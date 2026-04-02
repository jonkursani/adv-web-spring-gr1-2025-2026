package dev.jonkursani.restapigr1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity // Annotation based authorization
@EnableScheduling // Enable scheduled tasks (jobs)
public class RestApiGr1Application {

    public static void main(String[] args) {
        SpringApplication.run(RestApiGr1Application.class, args);
    }

}