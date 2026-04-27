package com.example.simpleapp.controller;

import com.example.simpleapp.dto.LoginForm;
import com.example.simpleapp.dto.UserRegistrationForm;
import com.example.simpleapp.model.AppUser;
import com.example.simpleapp.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private static final String SESSION_USER_ID = "loggedInUserId";

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String home(HttpSession session) {
        return session.getAttribute(SESSION_USER_ID) == null ? "redirect:/login" : "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String loginPage(Model model, HttpSession session) {
        if (session.getAttribute(SESSION_USER_ID) != null) {
            return "redirect:/dashboard";
        }

        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }
        if (!model.containsAttribute("userRegistrationForm")) {
            model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        HttpSession session,
                        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRegistrationForm", new UserRegistrationForm());
            return "login";
        }

        AppUser user = authService.authenticate(loginForm.getEmail(), loginForm.getPassword());
        if (user == null) {
            model.addAttribute("userRegistrationForm", new UserRegistrationForm());
            model.addAttribute("loginError", "Invalid email or password.");
            return "login";
        }

        session.setAttribute(SESSION_USER_ID, user.getId());
        session.setAttribute("loggedInUserName", user.getName());
        session.setAttribute("isAdmin", user.isAdmin());
        redirectAttributes.addFlashAttribute("successMessage", "Welcome back, " + user.getName() + ".");
        return "redirect:/dashboard";
    }

    @PostMapping("/register-user")
    public String registerUser(@Valid @ModelAttribute("userRegistrationForm") UserRegistrationForm form,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (!form.passwordsMatch()) {
            bindingResult.rejectValue("confirmPassword", "match", "Passwords do not match.");
        }

        if (!bindingResult.hasFieldErrors("email") && authService.emailExists(form.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "This email is already registered.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("loginForm", new LoginForm());
            return "login";
        }

        authService.registerUser(form);
        redirectAttributes.addFlashAttribute("successMessage", "Account created. Please log in.");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "You have been logged out.");
        return "redirect:/login";
    }
}
