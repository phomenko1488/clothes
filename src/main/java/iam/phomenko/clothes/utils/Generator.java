package iam.phomenko.clothes.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Generator {
    @Bean
    public Generator getGenerator() {
        return new Generator();
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }

    private Generator() {

    }
}
