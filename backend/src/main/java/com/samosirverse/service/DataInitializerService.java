package com.samosirverse.service;

import com.samosirverse.entity.User;
import com.samosirverse.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializerService implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializerService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializerService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Ensure default administrator account exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User(
                    "admin",
                    "admin@samosirverse.id",
                    passwordEncoder.encode("admin123"),
                    "Administrator SamosirVerse",
                    "ROLE_ADMIN"
            );
            userRepository.save(admin);
            log.info("Default administrator created: username=admin, password=admin123");
        }
    }
}
