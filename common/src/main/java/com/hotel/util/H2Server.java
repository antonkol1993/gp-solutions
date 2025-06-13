package com.hotel.util;

import org.h2.tools.Server;

public class H2Server {
    public static void main(String[] args) throws Exception {
        Server.createTcpServer(
            "-tcp",           // включить TCP
            "-tcpAllowOthers", // разрешить подключения не только с localhost (можно убрать)
            "-tcpPort", "9092", // порт по умолчанию
            "-ifNotExists"     // не падать, если уже запущен
        ).start();

        System.out.println("✅ H2 TCP сервер запущен на порту 9092");
        Thread.currentThread().join(); // держим поток, чтобы не завершался
    }
}
