package com.vector.authorbookrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.vector.authorbookrest","com.vector.authorbookcommon"})
@EntityScan(basePackages = "com.vector.authorbookcommon.entity")
@EnableJpaRepositories(basePackages = "com.vector.authorbookcommon.repository")
public class AuthorBookRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorBookRestApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

