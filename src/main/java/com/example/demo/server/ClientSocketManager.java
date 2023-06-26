package com.example.demo.server;

import java.net.Socket;

public class ClientSocketManager {
    private static ClientSocketManager instance = null;
    private Socket socket;

    private ClientSocketManager() {
    }

    public static ClientSocketManager getInstance() {
        if (instance == null) {
            instance = new ClientSocketManager();
        }
        return instance;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
}
