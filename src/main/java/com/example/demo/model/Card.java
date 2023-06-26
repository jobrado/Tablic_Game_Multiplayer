package com.example.demo.model;

import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.*;

public class Card implements Serializable{
    private final Suit suit;
    private final Value value;
    private  transient Image cardImage;
    private  String imageUrl;
    private final int cardPoints = 0;

    public  int getCardPoints() {
        return cardPoints;
    }
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
        {
                try {
                    in.defaultReadObject();
                    String url = (String) in.readObject();
                    if (url != null) {
                        cardImage = new Image(url);
                    }
                }
                catch (Exception e){
                    e.getMessage();
                }
        }


    @Serial
    private void writeObject(ObjectOutputStream s) throws IOException {
           s.defaultWriteObject();
            s.writeObject(cardImage == null ? null : cardImage.getUrl());

}
    public  int setCardPoints(Card card){
        if (card.getSuit().name().equals("clubs") && card.getValue().name().equals("two")){
            return 1;
        }
        else if (card.getSuit().name().equals("diamonds") && card.getValue().name().equals("ten")){
            return 2;
        }
        else if ( card.getValue().name().equals("ace") || card.getValue().name().equals("jack") || card.getValue().name().equals("queen") || card.getValue().name().equals("king")){
            return 1;
        }
        else {
            return 0;
        }

    }
    public Card(Suit suit, Value value, Image cardImage) {
        this.suit = suit;
        this.value = value;
        this.cardImage = cardImage;
        this.imageUrl = cardImage.getUrl();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public Image getCardImage() {
        return cardImage;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                '}';
    }
}
