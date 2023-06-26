package com.example.demo.clientModel;

import com.example.demo.model.Card;

import java.io.Serializable;
import java.util.List;

public class StartGameData implements Serializable {
    private List<Card> cardsOnTable;
    private List<Card> playerOneCards;
    private List<Card> playerTwoCards;


    public List<Card> getCardsOnTable() {
        return cardsOnTable;
    }

    public void setCardsOnTable(List<Card> cardsOnTable) {
        this.cardsOnTable = cardsOnTable;
    }

    public List<Card> getPlayerOneCards() {
        return playerOneCards;
    }

    public void setPlayerOneCards(List<Card> playerOneCards) {
        this.playerOneCards = playerOneCards;
    }

    public List<Card> getPlayerTwoCards() {
        return playerTwoCards;
    }

    public void setPlayerTwoCards(List<Card> playerTwoCards) {
        this.playerTwoCards = playerTwoCards;
    }

    @Override
    public String toString() {
        return "StartGameData{" +
                "cardsOnTable=" + cardsOnTable +
                ", playerOneCards=" + playerOneCards +
                ", playerTwoCards=" + playerTwoCards +
                '}';
    }
}
