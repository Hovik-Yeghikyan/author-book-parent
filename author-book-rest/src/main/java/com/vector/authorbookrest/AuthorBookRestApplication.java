package com.vector.authorbookrest;

import com.vector.authorbookcommon.entity.User;
import com.vector.authorbookcommon.entity.UserType;
import com.vector.authorbookcommon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.vector.authorbookrest","com.vector.authorbookcommon"})
@EntityScan(basePackages = "com.vector.authorbookcommon.entity")
@EnableJpaRepositories(basePackages = "com.vector.authorbookcommon.repository")
@EnableScheduling
public class AuthorBookRestApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(AuthorBookRestApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.findByEmail("admin@mail.com").isEmpty()) {
            userService.save(User.builder()
                    .email("admin@mail.com")
                    .name("admin")
                    .surname("admin")
                    .password(passwordEncoder().encode("admin"))
                    .userType(UserType.ADMIN)
                    .build());
        }
    }
}

