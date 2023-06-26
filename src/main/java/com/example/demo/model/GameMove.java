package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GameMove {
    private String playerName;
    private Card cardThrown;
    private List<Card> cardPicked;
    private LocalDateTime dateTime;
    private final DateTimeFormatter mf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public GameMove(String playerName, Card cardThrown, List<Card> cardPicked, LocalDateTime dateTime) {
        this.playerName = playerName;
        this.cardThrown = cardThrown;
        this.cardPicked = cardPicked;
        this.dateTime = dateTime;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Card getCardThrown() {
        return cardThrown;
    }

    public void setCardThrown(Card cardThrown) {
        this.cardThrown = cardThrown;
    }

    public List<Card> getCardPicked() {
        return cardPicked;
    }

    public void setCardPicked(List<Card> cardPicked) {
        this.cardPicked = cardPicked;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "GameMove:" +
                "player name = '" + playerName + '\'' +
                ", card thrown= " + cardThrown +
                ", card picked=" + cardPicked +
                ", date time=" + dateTime.format(mf) +
                '}';
    }
}
