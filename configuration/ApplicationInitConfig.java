package com.huynguyen.bbqrestaurantmanagement.configuration;

import com.huynguyen.bbqrestaurantmanagement.entity.Customer;
import com.huynguyen.bbqrestaurantmanagement.entity.Role;
import com.huynguyen.bbqrestaurantmanagement.entity.User;
import com.huynguyen.bbqrestaurantmanagement.repository.CustomerRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.RoleRepository;
import com.huynguyen.bbqrestaurantmanagement.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    RoleRepository roleRepository;
    UserRepository userRepository;
    CustomerRepository customerRepository;

    PasswordEncoder passwordEncoder;

    String adminDefault = "admin";
    String adminName = "ADMIN";

    @Bean
    @Transactional
    public ApplicationRunner applicationRunner() {
        return args -> {
            log.info("Starting Application Initialization (Roles)...");

            Role adminRole;
            // Kiểm tra xem role ADMIN có tồn tại hay chưa, nếu chưa tạo role ADMIN.
            if (roleRepository.findById(adminName).isEmpty()) {
                adminRole = Role
                        .builder()
                        .roleName(adminName)
                        .description("Administrator role with full access")
                        .build();

                // Lưu role ADMIN.
                roleRepository.save(adminRole);
                log.info("Role ADMIN created.");
            } else {
                adminRole = roleRepository.findById(adminName).get();
                log.info("Role ADMIN already existed");
            }

            // Tạo và kiểm tra tồn tại của role USER.
            if (roleRepository.findById("USER").isEmpty()) {
                Role userRole = Role
                        .builder()
                        .roleName("USER")
                        .description("Standard user role with limited access")
                        .build();

                // Lưu role USER
                roleRepository.save(userRole);
                log.info("Role USER created");
            } else {
                log.info("Role USER already existed");
            }

            // Tạo hoặc lấy ADMIN mặc định.
            if (userRepository.findByUserName(adminDefault).isEmpty()) {
                User adminUser = User
                        .builder()
                        .userName(adminDefault)
                        .password(passwordEncoder.encode(adminDefault))
                        .roles(Set.of(adminRole))
                        .build();

                String phoneNumber = "000000000";
                String email = "admin@example.com";
                if (customerRepository.existsByPhoneNumber(phoneNumber)) {
                    phoneNumber = "admin_" + System.currentTimeMillis();
                    log.warn("Phone number {} already exists, using {}", "000000000", phoneNumber);
                }
                if (customerRepository.existsByEmail(email)) {
                    email = "admin_" + System.currentTimeMillis() + "@example.com";
                    log.warn("Email {} already exists, using {}", "admin@example.com", email);
                }

                Customer adminCustomer = Customer
                        .builder()
                        .firstName("Admin")
                        .lastName("User")
                        .phoneNumber(phoneNumber)
                        .email(email)
                        .address("N/A")
                        .loyaltyPoints(0)
                        .user(adminUser)
                        .build();

                adminUser.setCustomer(adminCustomer);

                userRepository.save(adminUser);
                log.info("Default admin user created: username 'admin', password 'admin123'");
            } else {
                log.info("Default admin user already exists.");
            }

            log.info("Application Initialization (Roles) finished.");
        };
    }
}
