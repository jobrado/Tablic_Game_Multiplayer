package com.example.demo.clientModel;

import com.example.demo.model.Card;

import java.io.Serializable;

public class Move implements Serializable {
    private int cardRow;
    private int cardColumn;
    private String cardImageUrl;
    private Card card;

    public int getCardRow() {
        return cardRow;
    }

    public int getCardColumn() {
        return cardColumn;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public Card getCard() {
        return card;
    }

    public Move(int cardRow, int cardColumn, String cardImageUrl, Card card) {
        this.cardRow = cardRow;
        this.cardColumn = cardColumn;
        this.cardImageUrl = cardImageUrl;
        this.card = card;
    }

    @Override
    public String toString() {
        return "Move{" +
                "cardRow=" + cardRow +
                ", cardColumn=" + cardColumn +
                ", cardImageUrl='" + cardImageUrl + '\'' +
                '}';
    }
}
