package com.vector.authorbookweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.vector.authorbookweb","com.vector.authorbookcommon"})
@EntityScan(basePackages = "com.vector.authorbookcommon.entity")
@EnableJpaRepositories(basePackages = "com.vector.authorbookcommon.repository")
public class AuthorBookWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorBookWebApplication.class, args);
    }

}
