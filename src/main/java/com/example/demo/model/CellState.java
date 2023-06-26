package com.example.demo.model;

import java.io.Serializable;

public class CellState implements Serializable {
    private int coordinateX;
    private int coordinateY;
    private Card cardOnTable;

    public CellState(int coordinateX, int coordinateY, Card cardOnTable) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.cardOnTable = cardOnTable;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public Card getCardOnTable() {
        return cardOnTable;
    }

    public void setCardOnTable(Card cardOnTable) {
        this.cardOnTable = cardOnTable;
    }

    @Override
    public String toString() {
        return "CellState{" +
                "coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", cardOnTable=" + cardOnTable +
                '}';
    }
}
