package com.hotel;

import com.hotel.dao.repository.UserRepository;
import com.hotel.model.entity.Hotel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRepository repo) {
        return args -> {

        };
    }


}
