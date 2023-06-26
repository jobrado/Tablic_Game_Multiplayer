package com.example.demo.clientModel;


import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class PlayerData implements Serializable {
    private Integer id;
    private String playerName;
    private Integer port;
    private String ipAddress;
    private Boolean isServerConnected;
private ObjectOutputStream clientOos;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isServerConnected() {
        return isServerConnected;
    }

    public void setIsServerConnected(Boolean serverConnected) {
        isServerConnected = serverConnected;
    }

    public ObjectOutputStream getClientSocket() {
        return clientOos;
    }

    public void setClientSocket(ObjectOutputStream clientSocket) {
        this.clientOos = clientSocket;
    }

    public PlayerData(Integer port, String ipAddress, Boolean isServerConnected, ObjectOutputStream oos) {
        this.port = port;
        this.ipAddress = ipAddress;
        this.isServerConnected = isServerConnected;
        this.clientOos = oos;
    }

    public PlayerData(Integer port, String ipAddress, Boolean isServerConnected, String playerName, Integer id) {

        this.port = port;
        this.ipAddress = ipAddress;
        this.isServerConnected = isServerConnected;
        this.playerName = playerName;
        this.id = id;
    }

    public PlayerData(Integer port, String ipAddress, Boolean isServerConnected) {
        this.port = port;
        this.ipAddress = ipAddress;
        this.isServerConnected = isServerConnected;
    }

    public PlayerData(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", port=" + port +
                ", ipAddress='" + ipAddress + '\'' +
                ", isServerConnected=" + isServerConnected +
                '}';
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
