package com.hotel;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ApiAppApplication {
    public static void main(String[] args) throws SQLException {
//        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
        SpringApplication.run(ApiAppApplication.class, args);
    }
}
