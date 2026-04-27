package com.example.simpleapp.config;

import com.example.simpleapp.model.AppUser;
import com.example.simpleapp.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedDefaultUser(AppUserRepository appUserRepository) {
        return args -> {
            List<AppUser> usersWithMissingAdminFlag = appUserRepository.findByAdminIsNull();
            if (!usersWithMissingAdminFlag.isEmpty()) {
                usersWithMissingAdminFlag.forEach(user -> user.setAdmin(false));
                appUserRepository.saveAll(usersWithMissingAdminFlag);
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (appUserRepository.count() == 0) {
                appUserRepository.save(new AppUser(
                        "Admin User",
                        "admin@collegeportal.com",
                        encoder.encode("admin123"),
                        true
                ));
                return;
            }

            appUserRepository.findByEmailIgnoreCase("admin@collegeportal.com")
                    .ifPresent(user -> {
                        if (!user.isAdmin()) {
                            user.setAdmin(true);
                            appUserRepository.save(user);
                        }
                    });
        };
    }
}
