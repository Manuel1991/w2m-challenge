package org.w2m_challenge.fflix_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAspectJAutoProxy
@EnableJpaRepositories
@SpringBootApplication
public class FflixApp {
    public static void main(String[] args) {
        SpringApplication.run(FflixApp.class, args);
    }
}