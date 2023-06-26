package com.example.demo.clientModel;

import com.example.demo.model.Card;
import com.example.demo.model.CellState;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private List<CellState> cellStateList;
    private String playerOneName;
    private String playerTwoName;
  //  private String playerThreeName;
   // private String playerFourName;
   // private String playerThreeName;

    private Boolean playerOneTurn;
    private Boolean playerTwoTurn;
  //  private Boolean playerThreeTurn;
 //   private Boolean playerFourTurn;
    private List<Card> cardsOnTable;
    private List<Card> playerOneCardsPicked;
    private List<Card> playerTwoCardsPicked;
  //  private List<Card> playerThreeCardsPicked;
  //  private List<Card> playerFourCardsPicked;
    private List<Card> playerOneCurrentCardsList;
    private List<Card> playerTwoCurrentCardsList;
  //  private List<Card> playerThreeCurrentCardsList;
  //  private List<Card> playerFourCurrentCardsList;
    private List<ImageView> playerTwoCardsPlaceholder;
  //  private List<ImageView> playerThreeCardsPlaceholder;
   // private List<ImageView> playerFourCardsPlaceholder;
    private List<ImageView> playerOneCardsPlaceholder;

    public List<CellState> getCellStateList() {
        return cellStateList;
    }

    public void setCellStateList(List<CellState> cellStateList) {
        this.cellStateList = cellStateList;
    }

    public GameState() {
        this.cellStateList = new ArrayList<>();
    }

    public GameState(String playerOneName, Boolean playerOneTurn, Boolean playerTwoTurn, List<Card> cardsOnTable, List<Card> playerOneCardsPicked, List<Card> playerTwoCardsPicked, List<Card> playerOneCurrentCardsList, List<Card> playerTwoCurrentCardsList,  List<ImageView> playerTwoCardsPlaceholder, List<ImageView> playerOneCardsPlaceholder) {
        this.cellStateList = new ArrayList<>();
        this.playerOneName = playerOneName;
        this.playerOneTurn = playerOneTurn;
        this.playerTwoTurn = playerTwoTurn;
    //    this.playerThreeTurn = playerThreeTurn;
    //    this.playerFourTurn = playerFourTurn;
        this.cardsOnTable = cardsOnTable;
        this.playerOneCardsPicked = playerOneCardsPicked;
        this.playerTwoCardsPicked = playerTwoCardsPicked;
    //    this.playerThreeCardsPicked = playerThreeCardsPicked;
       // this.playerFourCardsPicked = playerFourCardsPicked;
        this.playerOneCurrentCardsList = playerOneCurrentCardsList;
        this.playerTwoCurrentCardsList = playerTwoCurrentCardsList;
     //   this.playerThreeCurrentCardsList = playerThreeCurrentCardsList;
      //  this.playerFourCurrentCardsList = playerFourCurrentCardsList;
        this.playerTwoCardsPlaceholder = playerTwoCardsPlaceholder;
    //    this.playerThreeCardsPlaceholder = playerThreeCardsPlaceholder;
     //   this.playerFourCardsPlaceholder = playerFourCardsPlaceholder;
        this.playerOneCardsPlaceholder = playerOneCardsPlaceholder;
    }

    public List<ImageView> getPlayerOneCardsPlaceholder() {
        return playerOneCardsPlaceholder;
    }

    public void setPlayerOneCardsPlaceholder(List<ImageView> playerOneCardsPlaceholder) {
        this.playerOneCardsPlaceholder = playerOneCardsPlaceholder;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public Boolean getPlayerOneTurn() {
        return playerOneTurn;
    }

    public void setPlayerOneTurn(Boolean playerOneTurn) {
        this.playerOneTurn = playerOneTurn;
    }

    public Boolean getPlayerTwoTurn() {
        return playerTwoTurn;
    }

    public void setPlayerTwoTurn(Boolean playerTwoTurn) {
        this.playerTwoTurn = playerTwoTurn;
    }

    public List<Card> getCardsOnTable() {
        return cardsOnTable;
    }

    public void setCardsOnTable(List<Card> cardsOnTable) {
        this.cardsOnTable = cardsOnTable;
    }

    public List<Card> getPlayerOneCardsPicked() {
        return playerOneCardsPicked;
    }

    public void setPlayerOneCardsPicked(List<Card> playerOneCardsPicked) {
        this.playerOneCardsPicked = playerOneCardsPicked;
    }

    public List<Card> getPlayerTwoCardsPicked() {
        return playerTwoCardsPicked;
    }

    public void setPlayerTwoCardsPicked(List<Card> playerTwoCardsPicked) {
        this.playerTwoCardsPicked = playerTwoCardsPicked;
    }

    public List<Card> getPlayerOneCurrentCardsList() {
        return playerOneCurrentCardsList;
    }

    public void setPlayerOneCurrentCardsList(List<Card> playerOneCurrentCardsList) {
        this.playerOneCurrentCardsList = playerOneCurrentCardsList;
    }

    public List<Card> getPlayerTwoCurrentCardsList() {
        return playerTwoCurrentCardsList;
    }

    public void setPlayerTwoCurrentCardsList(List<Card> playerTwoCurrentCardsList) {
        this.playerTwoCurrentCardsList = playerTwoCurrentCardsList;
    }
/*
    public List<Card> getPlayerThreeCurrentCardsList() {
        return playerThreeCurrentCardsList;
    }

    public void setPlayerThreeCurrentCardsList(List<Card> playerThreeCurrentCardsList) {
        this.playerThreeCurrentCardsList = playerThreeCurrentCardsList;
    }

    public List<Card> getPlayerFourCurrentCardsList() {
        return playerFourCurrentCardsList;
    }

    public void setPlayerFourCurrentCardsList(List<Card> playerFourCurrentCardsList) {
        this.playerFourCurrentCardsList = playerFourCurrentCardsList;
    }
*/
    public List<ImageView> getPlayerTwoCardsPlaceholder() {
        return playerTwoCardsPlaceholder;
    }

    public void setPlayerTwoCardsPlaceholder(List<ImageView> playerTwoCardsPlaceholder) {
        this.playerTwoCardsPlaceholder = playerTwoCardsPlaceholder;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "cellStateList=" + cellStateList +
                ", playerOneName='" + playerOneName + '\'' +
                ", playerTwoName='" + playerTwoName + '\'' +
                ", playerOneTurn=" + playerOneTurn +
                ", playerTwoTurn=" + playerTwoTurn +
                ", cardsOnTable=" + cardsOnTable +
                ", playerOneCardsPicked=" + playerOneCardsPicked +
                ", playerTwoCardsPicked=" + playerTwoCardsPicked +
                ", playerOneCurrentCardsList=" + playerOneCurrentCardsList +
                ", playerTwoCurrentCardsList=" + playerTwoCurrentCardsList +
                ", playerTwoCardsPlaceholder=" + playerTwoCardsPlaceholder +
                ", playerOneCardsPlaceholder=" + playerOneCardsPlaceholder +
                '}';
    }
}

