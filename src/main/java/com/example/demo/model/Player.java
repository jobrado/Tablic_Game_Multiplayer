package com.example.demo.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerInterface, Serializable {
    private String name;
    private static int points = 0;
    private static int cardsCollected = 0;

    public  int getCardsCollected() {
        return cardsCollected;
    }

    public  void setCardsCollected(int cardsCollected) {
        Player.cardsCollected = cardsCollected;
    }

    private List<Card> playerCards = new ArrayList<>() ;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        Player.points = points;
    }

    public Player() {

    }

    public Player(String name, List<Card> playerCards) {
        this.name = name;

        this.playerCards = playerCards;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card>  getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card>   playerCards) {
        this.playerCards = playerCards;
    }


}
