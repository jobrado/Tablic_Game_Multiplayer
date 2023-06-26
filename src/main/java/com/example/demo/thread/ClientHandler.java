package com.example.demo.thread;

import com.example.demo.clientModel.MessageData;
import com.example.demo.clientModel.MessageType;
import com.example.demo.clientModel.PlayerData;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientHandler implements  Runnable {
    private static MessageData gameStartState;

    private MessageData messageData;
    private MessageData receivedData;
    private final Socket clientSocket;
    private static PlayerData playerDataOne;
    private static  PlayerData playerDataTwo;
    private static MessageData moveData;
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static final String HOST = "localhost";
    public static final int PORT = 1989;
    public static Map<Integer, PlayerData> players = new HashMap<>();
    @Override
    public void run() {

        try (
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            players.put(clientSocket.getPort(), new PlayerData(clientSocket.getPort(), HOST, true, oos));
            System.out.println("players size is: " + players.size());

            System.out.println("Server is receiving player name from client");
            while (true){ try{
            MessageData playerNameFromClient =  (MessageData) ois.readObject();
            if (playerNameFromClient.getType() == MessageType.PLAYERDATA) {
                System.out.println("Player name: " + playerNameFromClient.getPlayerData().getPlayerName());

                System.out.println("Server is sending port and else to client: ");

                if (players.size() == 1) {
                    playerDataOne = new PlayerData(clientSocket.getPort(), clientSocket.getInetAddress().toString(), true, playerNameFromClient.getPlayerData().getPlayerName(), 1);
                    MessageData messageData = new MessageData(MessageType.PLAYERDATA, playerDataOne);
                    oos.writeObject(messageData);
                    System.out.println("Data sent to client 1: "+ clientSocket.getPort());

                } else if (players.size() == 2) {
                    playerDataTwo = new PlayerData(clientSocket.getPort(), clientSocket.getInetAddress().toString(), true, playerNameFromClient.getPlayerData().getPlayerName(), 2);
                    MessageData messageData = new MessageData(MessageType.PLAYERDATA, playerDataTwo);
                    oos.writeObject(messageData);
                    System.out.println("Client is connecting to " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                    System.out.println("Data sent to client 2: " + clientSocket.getPort());
                    for (Map.Entry<Integer, PlayerData> entry : players.entrySet()){
                         entry.getValue().getClientSocket().writeObject("STARTGAME");

                        }

                }


                   }
             else if (playerNameFromClient.getType() == MessageType.GAMESTART && playerDataOne.getPort()==clientSocket.getPort()) {
                            try {
                                System.out.println(playerNameFromClient.toString());
                                if (playerNameFromClient.getType() == MessageType.GAMESTART) {
                                    gameStartState = playerNameFromClient;
                                 //   oos.writeObject(playerNameFromClient);
                                    System.out.println("gss: : : : "+gameStartState);
                                    if (ois.available() == 0) {
                                        break;
                                    }
                                }
                            } catch (EOFException x) {
                                break;
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);

                        }}
             else if(playerNameFromClient.getType()==MessageType.CHECK && playerDataTwo.getPort()== clientSocket.getPort()){
                    System.out.println(ois.available());
                    System.out.println("Isprobavanje::: " + gameStartState.toString());
                    try {
                        oos.writeObject(gameStartState);
                        gameStartState = null;
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());

                    }
                    System.out.println("Success");

                }
            else if (playerNameFromClient.getType() == MessageType.CALLBACK){
                System.out.println("Game move passed to player, game move is " + moveData);
                oos.writeObject(moveData);
            }
             else if(playerNameFromClient.getType()==MessageType.GAMEMOVE){
                System.out.println("game move: q" + playerNameFromClient.toString());
                moveData = playerNameFromClient;
            }
            }
         catch(EOFException ex){
                    break;
                }}

                } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }}

    }

    






