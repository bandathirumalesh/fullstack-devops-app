package com.example.simpleapp.service;

import com.example.simpleapp.dto.UserRegistrationForm;
import com.example.simpleapp.model.AppUser;
import com.example.simpleapp.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public boolean emailExists(String email) {
        return appUserRepository.existsByEmailIgnoreCase(email);
    }

    public AppUser registerUser(UserRegistrationForm form) {
        AppUser appUser = new AppUser();
        appUser.setName(form.getName());
        appUser.setEmail(form.getEmail());
        appUser.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        appUser.setAdmin(false);
        return appUserRepository.save(appUser);
    }

    public AppUser authenticate(String email, String password) {
        return appUserRepository.findByEmailIgnoreCase(email)
                .filter(user -> passwordEncoder.matches(password, user.getPasswordHash()))
                .orElse(null);
    }
}
