package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User cgds = new User("cagdas@gmail.com", "password");
            User cgds2 = new User("cagdas2@gmail.com", "password");

            repository.saveAll(List.of(cgds, cgds2));
        };
    }
}
