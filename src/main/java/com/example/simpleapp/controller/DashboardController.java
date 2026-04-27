package com.example.simpleapp.controller;

import com.example.simpleapp.dto.StudentForm;
import com.example.simpleapp.service.StudentService;
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
public class DashboardController {

    private static final String SESSION_USER_ID = "loggedInUserId";

    private final StudentService studentService;

    public DashboardController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute(SESSION_USER_ID) == null) {
            return "redirect:/login";
        }

        boolean isAdmin = Boolean.TRUE.equals(session.getAttribute("isAdmin"));

        if (!model.containsAttribute("studentForm")) {
            model.addAttribute("studentForm", new StudentForm());
        }
        model.addAttribute("students", isAdmin ? studentService.findAll() : java.util.List.of());
        model.addAttribute("studentCount", isAdmin ? studentService.findAll().size() : null);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("loggedInUserName", session.getAttribute("loggedInUserName"));
        return "dashboard";
    }

    @PostMapping("/students")
    public String registerStudent(@Valid @ModelAttribute("studentForm") StudentForm studentForm,
                                  BindingResult bindingResult,
                                  HttpSession session,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if (session.getAttribute(SESSION_USER_ID) == null) {
            return "redirect:/login";
        }

        if (!bindingResult.hasFieldErrors("rollNumber") && studentService.rollNumberExists(studentForm.getRollNumber())) {
            bindingResult.rejectValue("rollNumber", "duplicate", "Roll number already exists.");
        }

        if (bindingResult.hasErrors()) {
            boolean isAdmin = Boolean.TRUE.equals(session.getAttribute("isAdmin"));
            model.addAttribute("students", isAdmin ? studentService.findAll() : java.util.List.of());
            model.addAttribute("studentCount", isAdmin ? studentService.findAll().size() : null);
            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("loggedInUserName", session.getAttribute("loggedInUserName"));
            return "dashboard";
        }

        studentService.registerStudent(studentForm);
        redirectAttributes.addFlashAttribute("successMessage", "Student registered successfully.");
        return "redirect:/dashboard";
    }
}
