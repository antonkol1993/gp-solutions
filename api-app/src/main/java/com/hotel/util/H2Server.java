//package com.hotel.util;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import org.h2.tools.Server;
//import org.springframework.stereotype.Component;
//
//import java.sql.SQLException;
//
//@Component
//public class H2Server {
//
//
//
//    private Server tcpServer;
//
//    @PostConstruct
//    public void start() throws SQLException {
//        tcpServer = Server.createTcpServer(
//                "-tcp",
//                "-tcpAllowOthers",
//                "-tcpPort", "9092",
//                "-ifNotExists"
//        ).start();
//        System.out.println("✅ H2 TCP сервер запущен");
//    }
//
//    @PreDestroy
//    public void stop() {
//        if (tcpServer != null) {
//            tcpServer.stop();
//            System.out.println("🛑 H2 TCP сервер остановлен");
//        }
//    }
//
//}
