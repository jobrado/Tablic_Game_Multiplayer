package com.example.demo.model;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class DeckOfCards {
    //KORISTITI SET????
   // private final Card[] cards = new Card[52];
    private static final List <Card> cards = new ArrayList<>();
    public DeckOfCards() {
        fill();
    }
   private static List<Path> setImagePaths(){
        List<Path> imgs = new ArrayList<>();
        try (Stream<Path> filePathStream= Files.walk(Paths.get("C:\\Users\\Jo\\Documents\\Svi_predmeti\\5.PETI_SEMESTAR\\JAVA2\\Tablic_Game\\src\\main\\resources\\images"))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    imgs.add(filePath);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return imgs;
    }

    public static void fill() {
        List<Path> imgs = setImagePaths();
        for (Suit suit : Suit.values()) {
            for (Value v : Value.values()) {
                String name = v.name()+ "_of_" + suit.name()+".png";

                for (Path img : imgs) {
                    if (img.getFileName().toString().equals(name.toLowerCase())){

                        Card card = new Card(suit, v, new Image(img.toString()));
                        cards.add(card);
                        card.setCardPoints(card);

                    }
                }
            }
        }
    }
    public static List<Card> getCards() {

        return cards;
    }
    public static List<Card> getAllCards() {
        List<Card> allCards = new ArrayList<>();
        List<Path> imgs = setImagePaths();
        for (Suit suit : Suit.values()) {
            for (Value v : Value.values()) {
                String name = v.name() + "_of_" + suit.name() + ".png";

                for (Path img : imgs) {
                    if (img.getFileName().toString().equals(name.toLowerCase())) {

                        Card card = new Card(suit, v, new Image(img.toString()));
                        allCards.add(card);
                        card.setCardPoints(card);

                    }
                }
            }
        }
            return allCards;

    }
   /* public Card[] getCards() {
        return cards;
    }*/
//shuffle ne treba jer imam u svakom deljenju random
  /*  public Card[] shuffleCards(){

        Random rnd = new Random();
        for (int i = 0; i < cards.size(); i++) {
            cards[i] = cards.get(rnd.nextInt(cards.size()));
        }
        return cards;
    }*/

    public static List<Card> dealCards(){
         final List<Card> playerCards = new ArrayList<>();
        Random rnd = new Random();
        try{
            for (int i = 0; i < 6; i++) {
                Card card = cards.get(rnd.nextInt(cards.size()));
               playerCards.add(card);
              //  playerCards[i] = card;
                cards.remove(card);

            }
        return  playerCards;
    }
        catch (Exception e){
            e.getMessage();
        }
        return  playerCards;
    }
    public static List<Card> putFourCardsOnTable(){
        List<Card> cardsOnTable = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 4; i++) {
            Card card = cards.get(rnd.nextInt(cards.size()));
            cardsOnTable.add(card);
            cards.remove(card);
        }
        return  cardsOnTable;
    }
//u metodi dealcards moze ici i po 3 karte
}

