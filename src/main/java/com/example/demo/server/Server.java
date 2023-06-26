package com.example.demo.server;

import com.example.demo.clientModel.MessageData;
import com.example.demo.clientModel.MessageType;
import com.example.demo.clientModel.PlayerData;
import com.example.demo.model.GameMove;
import com.example.demo.model.Player;
import com.example.demo.thread.ClientHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private static ExecutorService executorService;

    public static final String HOST = "localhost";
    public static final int PORT = 1989;

    public static void main(String[] args) {
        executorService = Executors.newFixedThreadPool(2);
        acceptRequests();
    }

    private static void acceptRequests() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            System.err.println("Server listening on port: " + serverSocket.getLocalPort());

            while (true) {

                    Socket clientSocket = serverSocket.accept();

                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    executorService.execute(clientHandler);
                }

            }

        catch (IOException e) {
            e.printStackTrace();
        }
    }


  }