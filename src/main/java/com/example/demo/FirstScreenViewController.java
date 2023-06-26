package com.example.demo;

import com.example.demo.Utils.SceneUtil;
import com.example.demo.clientModel.MessageData;
import com.example.demo.clientModel.MessageType;
import com.example.demo.clientModel.PlayerData;
import com.example.demo.server.ClientSocketManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;


public class FirstScreenViewController {
    public static final String HOST = "localhost";
    public static final int PORT = 1989;
    private static PlayerData firstPlayerData;

    public static PlayerData getFirstPlayerData() {
        return firstPlayerData;
    }

    public static PlayerData getSecondPlayerData() {
        return secondPlayerData;
    }


    private static PlayerData secondPlayerData;
    @FXML
    public TextField tfPlayerName;
    @FXML
    private Label playerNameLabel;
    public static String playerOneName;
    public static Socket clientSocket;
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    public void initialize() {
        playerNameLabel.setText(HelloApplication.getPlayerName());
    }

    @FXML
    private void StartGame() {


        if (tfPlayerName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska!");
            alert.setHeaderText(null);
            alert.setContentText("Molim upisite ime igraca!");

            alert.showAndWait();
        } else {
            playerOneName = tfPlayerName.getText().trim();


        }
        try {
            clientSocket = new Socket(HOST, PORT);
            clientSocket.setKeepAlive(true);

            ClientSocketManager.getInstance().setSocket(clientSocket);
            System.out.println("Client is connecting to " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
            ReceiveSerializableRequest(clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ReceiveSerializableRequest(Socket clientSocket) {
        try {
             oos = new ObjectOutputStream(clientSocket.getOutputStream());
             ois = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Client sending player name to server");
            MessageData messageData = new MessageData(MessageType.PLAYERDATA, (new PlayerData(tfPlayerName.getText())));
            oos.writeObject(messageData);
            MessageData playerDataFromServer;

            while ((playerDataFromServer = (MessageData) ois.readObject()) != null) {

                if (playerDataFromServer.getType() == MessageType.PLAYERDATA) {

                    System.out.println("Client is reading player name from server " + playerDataFromServer.getPlayerData().getPlayerName());
                    System.out.println("Client is reading player port from server " + playerDataFromServer.getPlayerData().getPort());

                    if (playerDataFromServer.getPlayerData().getId() == 1) {
                        firstPlayerData = playerDataFromServer.getPlayerData();
                    } else {
                        secondPlayerData = playerDataFromServer.getPlayerData();
                    }
                    if (ois.available() == 0) {
                        break;
                    }

                }
            }
            boolean startGameReceived = true;

            while (startGameReceived) {
                Object receivedObject = ois.readObject();
                if (receivedObject instanceof String && receivedObject.equals("STARTGAME")) {
                    startGameReceived = false;
                    System.out.println("Starting new screen");
                    startNewScreen();
                    break;
                } else {
                    continue;
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }

        private void startNewScreen () {
            if (getFirstPlayerData() != null) {
                SceneUtil.setNewSceneToStage("view/second-screen-view.fxml", 900, 900, "Tablic! Welcome player #" + getFirstPlayerData().getId());
            } else {
                SceneUtil.setNewSceneToStage("view/second-screen-view.fxml", 900, 900, "Tablic! Welcome player #" + getSecondPlayerData().getId());
            }

        }
    }
