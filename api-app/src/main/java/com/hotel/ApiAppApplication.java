package com.hotel;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ApiAppApplication {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Server.createTcpServer(
                "-tcp",           // включить TCP
                "-tcpAllowOthers", // разрешить подключения не только с localhost (можно убрать)
                "-tcpPort", "9092", // порт по умолчанию
                "-ifNotExists"     // не падать, если уже запущен
        ).start();
        System.out.println("✅ H2 TCP сервер запущен на порту 9092");

        SpringApplication.run(ApiAppApplication.class, args);
    }
}
