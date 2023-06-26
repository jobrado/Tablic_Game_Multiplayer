package com.example.demo.rmi;
import com.example.demo.model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote{
    String REMOTE_OBJECT_NAME = "hr.algebra.rmi.service";

    void SendMessage(String message, String playerName) throws RemoteException;

    String receiveConversation() throws RemoteException;

}

