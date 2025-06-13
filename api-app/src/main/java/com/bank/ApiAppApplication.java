package com.bank;

import com.bank.dao.repository.UserRepository;
import com.bank.model.entity.UserEntity;
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
            repo.save(new UserEntity(null, "Test Name","antonkol1993@gmail.com"));
            System.out.println("✅ Данные успешно добавлены в БД");
        };
    }

}
