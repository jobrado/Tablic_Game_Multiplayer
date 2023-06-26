package com.example.demo.rmi;

import com.example.demo.model.Player;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RemoteServiceImpl implements  RemoteService{
    private String chatHistory;
    private List<Player> clients;

    public RemoteServiceImpl(){
        chatHistory="";
        clients = new ArrayList<>();
    }
    @Override
    public void SendMessage(String message, String playerName) throws RemoteException {

        LocalDateTime currenttime = LocalDateTime.now();
        chatHistory += currenttime.format(DateTimeFormatter.ISO_LOCAL_DATE) +":: "+ playerName + " > " + message + "\n";

    }

    @Override
    public String receiveConversation() throws RemoteException {
        return chatHistory;
    }


}
