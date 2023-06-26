package com.example.demo;

import com.example.demo.clientModel.*;
import com.example.demo.model.*;
import com.example.demo.rmi.RemoteService;
import com.example.demo.rmi.RmiServer;
import com.example.demo.server.ClientSocketManager;
import com.example.demo.thread.ClientHandler;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.*;


public class SecondScreenViewController {
    public static final String SACEKAJ_SVOJ_RED = "Sacekaj svoj red!!!";
    public static final String GOTOVA_IGRA = "Gotova igra!!!";
    public static final String VI_STE_NA_POTEZU = "Vi ste na potezu!!!";
    public static List<Card> cardsOnTable;
    @FXML
    private ImageView imgDeckOfCards;
    @FXML
    private ImageView cardPlaceholder1;
    @FXML
    private ImageView cardPlaceholder2;
    @FXML
    private ImageView cardPlaceholder3;
    @FXML
    private ImageView cardPlaceholder4;
    @FXML
    private ImageView cardPlaceholder5;
    @FXML
    private ImageView cardPlaceholder6;
    @FXML
    private ImageView playerTwoCardPlaceholder1;
    @FXML
    private ImageView playerTwoCardPlaceholder2;
    @FXML
    private ImageView playerTwoCardPlaceholder3;
    @FXML
    private ImageView playerTwoCardPlaceholder4;
    @FXML
    private ImageView playerTwoCardPlaceholder5;
    @FXML
    private ImageView playerTwoCardPlaceholder6;

    /*---------------------------*/
    @FXML
    private Label playerOneNameLabel;
    @FXML
    private Label playerTwoNameLabel;

    public static final int NUMBER_OF_ROWS = 4;
    public static final int NUMBER_OF_COLUMNS = 6;
    public final static ImageView[][] board = new ImageView[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
    public static Map<String, Integer> allScores = new HashMap<>();
    @FXML
    private ImageView zeroZero;
    @FXML
    private ImageView oneZero;
    @FXML
    private ImageView twoZero;
    @FXML
    private ImageView threeZero;
    @FXML
    private ImageView fourZero;
    @FXML
    private ImageView fiveZero;
    @FXML
    private ImageView zeroOne;
    @FXML
    private ImageView oneOne;
    @FXML
    private ImageView twoOne;
    @FXML
    private ImageView threeOne;
    @FXML
    private ImageView fourOne;
    @FXML
    private ImageView fiveOne;
    @FXML
    private ImageView zeroTwo;
    @FXML
    private ImageView oneTwo;
    @FXML
    private ImageView twoTwo;
    @FXML
    private ImageView threeTwo;
    @FXML
    private ImageView fourTwo;
    @FXML
    private ImageView fiveTwo;
    @FXML
    private ImageView zeroThree;
    @FXML
    private ImageView oneThree;
    @FXML
    private ImageView fiveThree;
    @FXML
    private ImageView twoThree;
    @FXML
    private ImageView threeThree;
    @FXML
    private ImageView fourThree;
    @FXML
    GridPane mainGridPane;
    @FXML
    private TextArea taMessageHistory;
    @FXML
    private TextField tfSendMessage;
    @FXML
    private Button btnSendMessage;

    private int cardValue;

    public static List<ImageView> playedCardsList = new ArrayList<>();
    public static List<ImageView> playerTwoCardPlaceholders = new ArrayList<>();
    public static List<ImageView> playerOneCardPlaceholders = new ArrayList<>();

    public static Player playerOne = new Player();
    public static Player playerTwo = new Player();

    public static boolean playerOneTurn = true;

    private static Card cardThrown;
    public static List<Card> playerOneCardPicked = new ArrayList<>();
    public static List<Card> playerTwoCardPicked = new ArrayList<>();

    public static List<GameMove> lastGameMoves = new ArrayList<>();
    static int playerCardCollected2 = 0;
    static int playerCardCollected3 = 0;
    static int playerCardCollected4 = 0;
    static int i = 0;
    static int collectedCards = 0;
   public static PlayerData playerData;
    private StartGameData startGameData = new StartGameData();
    private StartGameData gameStartFromServer;
    private Socket socket = FirstScreenViewController.clientSocket;
    private ObjectOutputStream oos = FirstScreenViewController.oos ;
    private ObjectInputStream ois = FirstScreenViewController.ois ;
    private int cardRow;
    private int cardColumn;
    private String cardImageDropped;
    private MessageData cardDragged;
    private Boolean flag =true;
    private RemoteService stub = null;
    public SecondScreenViewController() throws IOException {
    }

    /*---------------------------*/
    public void initialize() {
        DeckOfCards.fill();
        board[0][0] = zeroZero;
        board[0][1] = oneZero;
        board[0][2] = twoZero;
        board[0][3] = threeZero;
        board[0][4] = fourZero;
        board[0][5] = fiveZero;
        board[1][0] = zeroOne;
        board[1][1] = oneOne;
        board[1][2] = twoOne;
        board[1][3] = threeOne;
        board[1][4] = fourOne;
        board[1][5] = fiveOne;
        board[2][0] = zeroTwo;
        board[2][1] = oneTwo;
        board[2][2] = twoTwo;
        board[2][3] = threeTwo;
        board[2][4] = fourTwo;
        board[2][5] = fiveTwo;
        board[3][0] = zeroThree;
        board[3][1] = oneThree;
        board[3][2] = twoThree;
        board[3][3] = threeThree;
        board[3][4] = fourThree;
        board[3][5] = fiveThree;

        if (FirstScreenViewController.getFirstPlayerData() != null && FirstScreenViewController.getFirstPlayerData().getId() == 1) {
            playerData = FirstScreenViewController.getFirstPlayerData();

        } else {
            playerData = FirstScreenViewController.getSecondPlayerData();
        }

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("localhost", 1099);
            stub = (RemoteService) registry.lookup(RemoteService.REMOTE_OBJECT_NAME);

        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> refreshMessage()).start();


        System.out.println("second screen open for " + playerData);

        if (playerData.getId() == 2 /*&& receiveGameStartDataFromServer() != null && receiveGameStartDataFromServer().getType() == MessageType.GAMESTART*/) {
                gameStartFromServer = receiveGameStartDataFromServer().getStartGameData();
            }
            initializeTeams();
            initializeCards();
            playerOneTurn = true;
            if (playerData.getId() == 1) {
                MessageData messageData = new MessageData(MessageType.GAMESTART, startGameData);
                sendGamaDataToServer(messageData);
            }
        try {
            startGame();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void SendMessage() {
        String message = tfSendMessage.getText();
        try {
            stub.SendMessage(message, playerData.getPlayerName());
            String conversation = stub.receiveConversation();
            taMessageHistory.setText(conversation);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    public void refreshMessage(){
        while(true){
            try {
                Thread.sleep(500);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        readMessagesFromStub();
                    }
                });
                    } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public synchronized void readMessagesFromStub(){
        String messages = null;

        while (RmiServer.messageIsBeingWritten){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        RmiServer.messageIsBeingWritten = true;
        clearTextArea();

        try {
            messages = stub.receiveConversation();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        editTextArea(messages);

        RmiServer.messageIsBeingWritten = false;

        notifyAll();
    }
    public void editTextArea(String string){
        taMessageHistory.setText(string);
    }
    public void clearTextArea(){
        taMessageHistory.setText("");
    }
            private void startGame() throws InterruptedException {
        if(playerData.getId() == 2){
                startListening();}
        }


    public void startListening() {
       ListenForTheMove(ois, oos);
       playerOneTurn=true;
    }

    public void startListening1() {
        System.out.println("Listening for the next move started!!!");
        ListenForTheMove(ois1, oos1);
        playerOneTurn=true;
    }


private  void ListenForTheMove(ObjectInputStream ois, ObjectOutputStream oos){
    new Thread(() -> {
        while (flag) {
            try {
                int o = 0;
                System.out.println("Test " + o++ + ": " +socket.isConnected());
                Thread.sleep(5000);

                MessageData md = new MessageData(MessageType.CALLBACK, "Start");
                oos.writeObject(md);

                MessageData message = (MessageData)ois.readObject();
                if(message == null){
                    Thread.sleep(10000);
                }
                if (message.getType() == MessageType.GAMEMOVE) {
                    System.out.println(message);
                    if(message.getMoves() !=null){
                        for (Move move: message.getMoves()) {
                            board[move.getCardRow()][move.getCardColumn()].setImage(null);
                            board[move.getCardRow()][move.getCardColumn()].setUserData(null);
                            if(!playerTwoCardPlaceholders.isEmpty()) {
                                playerTwoCardPlaceholders.get( playerTwoCardPlaceholders.size()-1).setImage(null);
                            }
                            flag = false;
                            System.out.println(playerData.getId() + " id: move received: " + move);
                        }
                    }else{
                    Image image=new Image(message.getMove().getCardImageUrl());
                    board[message.getMove().getCardRow()][message.getMove().getCardColumn()].setImage(image);
                    board[message.getMove().getCardRow()][message.getMove().getCardColumn()].setUserData(message.getMove().getCard());
                        System.out.println(playerData.getId() + " id: move received: " + message.getMove());

                        if(!playerTwoCardPlaceholders.isEmpty()) {
                        playerTwoCardPlaceholders.get( playerTwoCardPlaceholders.size()-1).setImage(null);
                    }
                    flag = false;}

                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }).start();
}

    private synchronized MessageData receiveGameStartDataFromServer() {
        if(playerData.getId() == 2) {
            try  {
                Thread.sleep(3000);

                System.out.println("Client reading game start data from server");
                MessageData dataRead;
                MessageData md = new MessageData(MessageType.CHECK, "GAMESTARTDATA");
                while(true) {
                    System.out.println("ois: " +ois.available());
                    try{
                        oos.writeObject(md);
                        dataRead = (MessageData) ois.readObject();
                        if (dataRead.getType()==MessageType.GAMESTART){
                        System.out.println(dataRead);
                        return dataRead;}
                        else{
                            break;
                        }
                    }
                    catch(EOFException ex){
                        break;
                    }
                }


            } catch (IOException | ClassNotFoundException  e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


    private void sendGamaDataToServer(MessageData messageData) {
        if (playerData.getId() == 1 && socket.isConnected()) {
            try  {

                System.out.println("Client sending game start data to server");
               oos.writeObject(messageData);

            } catch (IOException e) {
                System.out.println("EERRROORR" + e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("jevbfs");
            // Handle the error...
        }

    }

    private void initializeCards() {
        if(playerData.getId() == 1) {
            cardsOnTable = DeckOfCards.putFourCardsOnTable();
            startGameData.setCardsOnTable(cardsOnTable);
            setCards(cardsOnTable);
        }
        else{
            setCards(gameStartFromServer.getCardsOnTable());
            System.out.println("karte na stolu su:" + gameStartFromServer.getCardsOnTable());
        }


    }
    private void setCards(List<Card> cardsOnTable){

        twoOne.setImage(new Image(cardsOnTable.get(0).getImageUrl()));
        twoOne.setUserData(cardsOnTable.get(0));
        threeOne.setImage(new Image(cardsOnTable.get(1).getImageUrl()));
        threeOne.setUserData(cardsOnTable.get(1));
        twoTwo.setImage(new Image(cardsOnTable.get(2).getImageUrl()));
        twoTwo.setUserData(cardsOnTable.get(2));
        threeTwo.setImage(new Image(cardsOnTable.get(3).getImageUrl()));
        threeTwo.setUserData(cardsOnTable.get(3));
    }
    void initializeTeams() {
      //  playerOne.setName(ClientThread.getFirstPlayerData().getPlayerName());
       if(playerData.getId() == 1) {
           playerOne.setPlayerCards(DeckOfCards.dealCards());
           startGameData.setPlayerOneCards(playerOne.getPlayerCards());
           playerTwo.setPlayerCards(DeckOfCards.dealCards());
           startGameData.setPlayerTwoCards(playerTwo.getPlayerCards());
       }
       else{
           playerTwo.setPlayerCards(gameStartFromServer.getPlayerOneCards());
           playerOne.setPlayerCards(gameStartFromServer.getPlayerTwoCards());
       }
     //   playerOneNameLabel.setText(FirstScreenViewController.getFirstPlayerData().getPlayerName());
     //   playerTwoNameLabel.setText(FirstScreenViewController.getSecondPlayerData().getPlayerName());

        drawPlayerCards();
    }

    private void drawPlayerCards() {
        cardPlaceholder1.setPickOnBounds(true);
        cardPlaceholder2.setPickOnBounds(true);
        cardPlaceholder3.setPickOnBounds(true);
        cardPlaceholder4.setPickOnBounds(true);
        cardPlaceholder5.setPickOnBounds(true);
        cardPlaceholder6.setPickOnBounds(true);
    if(playerData.getId() == 1) {
        connectImagesWithCardsOnTable();
    }
    else{
        connectImagesWithCardsOnTable();
    }
        playerOneCardPlaceholders.add(cardPlaceholder1);
        playerOneCardPlaceholders.add(cardPlaceholder2);
        playerOneCardPlaceholders.add(cardPlaceholder3);
        playerOneCardPlaceholders.add(cardPlaceholder4);
        playerOneCardPlaceholders.add(cardPlaceholder5);
        playerOneCardPlaceholders.add(cardPlaceholder6);

        playerTwoCardPlaceholders.add(playerTwoCardPlaceholder1);
        playerTwoCardPlaceholders.add(playerTwoCardPlaceholder2);
        playerTwoCardPlaceholders.add(playerTwoCardPlaceholder3);
        playerTwoCardPlaceholders.add(playerTwoCardPlaceholder4);
        playerTwoCardPlaceholders.add(playerTwoCardPlaceholder5);
        playerTwoCardPlaceholders.add(playerTwoCardPlaceholder6);

    }
    private void connectImagesWithCardsOnTable(){
        cardPlaceholder1.setImage(new Image(playerOne.getPlayerCards().get(0).getImageUrl()));
        cardPlaceholder1.setUserData(playerOne.getPlayerCards().get(0));
        cardPlaceholder2.setImage(new Image(playerOne.getPlayerCards().get(1).getImageUrl()));
        cardPlaceholder2.setUserData(playerOne.getPlayerCards().get(1));
        cardPlaceholder3.setImage(new Image(playerOne.getPlayerCards().get(2).getImageUrl()));
        cardPlaceholder3.setUserData(playerOne.getPlayerCards().get(2));
        cardPlaceholder4.setImage(new Image(playerOne.getPlayerCards().get(3).getImageUrl()));
        cardPlaceholder4.setUserData(playerOne.getPlayerCards().get(3));
        cardPlaceholder5.setImage(new Image(playerOne.getPlayerCards().get(4).getImageUrl()));
        cardPlaceholder5.setUserData(playerOne.getPlayerCards().get(4));
        cardPlaceholder6.setImage(new Image(playerOne.getPlayerCards().get(5).getImageUrl()));
        cardPlaceholder6.setUserData(playerOne.getPlayerCards().get(5));
    }
    private void clear() {
        i = 0;
        playedCardsList.clear();

    }

    public void resetCurrentHand() {
        clear();
    }
public void otherPlayerPlays(){
        flag = true;
        startListening1();
}
    public void getCardValue(MouseEvent mouseEvent) {

        if (((ImageView) mouseEvent.getSource()).getImage() == null) {
            return;
        }

        if (playerOneTurn) {
            try {
                animateToGrowSelectedCard((ImageView) mouseEvent.getSource());
                playedCardsList.add(((ImageView) mouseEvent.getSource()));
                Card card = (Card) ((ImageView) mouseEvent.getSource()).getUserData();
                cardValue = card.getValue().GetValue();

                //              collectedCards++;
                int i = calculateValues(cardValue);
                System.out.println(card);
                System.out.println(i);
                playerOneCardPicked.add(card);
                cardsOnTable.remove(card);

                ImageView imageView = ((ImageView) mouseEvent.getSource());

                cardRow = GridPane.getRowIndex(imageView);
                cardColumn = GridPane.getColumnIndex(imageView);
                Move move = new Move(cardRow, cardColumn,((ImageView) mouseEvent.getSource()).getImage().getUrl(), card );

                moves.add(move);
                System.out.println(moves);
                for (Move move1: moves
                ) {
                    System.out.println("move1"+move1);
                }


            } catch (NullPointerException e) {
                e.getMessage();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(SACEKAJ_SVOJ_RED);
            alert.setContentText(SACEKAJ_SVOJ_RED);

            alert.showAndWait();
        }
    }


    private void animateToGrowSelectedCard(ImageView source) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(500), source);
        scale.setByX(0.5f);
        scale.setByY(0.5f);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
        scale.play();
    }


    private int calculateValues(int cardValue) {

        i += cardValue;
        return i;
    }

    public void setOnDragDetected(MouseEvent mouseEvent) {
        if (!playerOneTurn) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(SACEKAJ_SVOJ_RED);
            alert.setContentText(SACEKAJ_SVOJ_RED);

            alert.showAndWait();
        } else {
            Dragboard db = ((ImageView) mouseEvent.getSource()).startDragAndDrop(TransferMode.ANY);
            cardThrown = (Card) ((ImageView) mouseEvent.getSource()).getUserData();
            cardImageDropped= cardThrown.getImageUrl();
            playerOne.getPlayerCards().remove(cardThrown);
            recordGameMove(playerOne, cardThrown, playerOneCardPicked);
          //  cardsOnTable.add(cardThrown);

            ClipboardContent content = new ClipboardContent();
            content.putImage(((ImageView) mouseEvent.getSource()).getImage());
            db.setContent(content);
        }
    }

    public void setOnDragDropped(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        if (db.hasImage()) {
            System.out.println("Dropped: " + db.getImage());
            ((ImageView) dragEvent.getSource()).setImage(db.getImage());
            ((ImageView) dragEvent.getSource()).setUserData(cardThrown);
            System.out.println("test " + cardThrown);
            dragEvent.setDropCompleted(true);

            ImageView imageView = (ImageView) dragEvent.getSource();


            cardRow = GridPane.getRowIndex(imageView);
            cardColumn = GridPane.getColumnIndex(imageView);
            System.out.println("row: " + cardRow + " col: " + cardColumn);
        //    cardImageDropped = imageView.getImage().getUrl();
           // cardDragged.setGameState(new GameState());
            MessageData messageData = new MessageData(MessageType.GAMEMOVE, new Move(cardRow, cardColumn, cardImageDropped, cardThrown));
            try {
                sendGamaMoveDataToServer(messageData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(messageData);
            System.out.println(playerData.getId());
            dragEvent.setDropCompleted(true);
            dragEvent.consume();
        } else {
            dragEvent.setDropCompleted(false);
        }
        dragEvent.consume();
    }
    Socket socket1 ;
    ObjectOutputStream oos1;
    ObjectInputStream ois1;
    private void sendGamaMoveDataToServer(MessageData messageData) throws IOException {
        socket.close();
        oos.flush();
        oos.close();
        ois.close();
        if (socket1 == null) {
            socket1 = new Socket("localhost", 1989);
            oos1 = new ObjectOutputStream(socket1.getOutputStream());
            ois1 = new ObjectInputStream(socket1.getInputStream());
            if (socket1.isConnected()) {
                try {

                    System.out.println("Client sending game move data to server");
                    oos1.writeObject(messageData);

                } catch (IOException e) {
                    System.out.println("EERRROORR" + e.getMessage());
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("jevbfs");
                // Handle the error...
            }
        }
        else{
            try {

                System.out.println("Client sending game move data to server");
                oos1.writeObject(messageData);

            } catch (IOException e) {
                System.out.println("EERRROORR" + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
    public void setOnDragOver(DragEvent dragEvent) {
        if (((ImageView) dragEvent.getSource()).getImage() != null) {
            return;
        } else {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
            dragEvent.consume();
        }
    }


    public void setOnDragDone(DragEvent dragEvent) {
        if (dragEvent.getTransferMode() == TransferMode.MOVE) {

            mainGridPane.getChildren().remove(((ImageView) dragEvent.getSource()));
            ((ImageView) dragEvent.getSource()).setUserData(null);
            //     ((ImageView) dragEvent.getSource()).setImage(null);
            playerOneTurn = false;

        }
        dragEvent.consume();
    }
 private  List<Move>moves= new ArrayList<>();

    public void pickUpCardsOffTheTable(MouseEvent mouseEvent) {
        if (!playerOneTurn) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(SACEKAJ_SVOJ_RED);
            alert.setContentText(SACEKAJ_SVOJ_RED);

            alert.showAndWait();
        } else {
            try {
                if (((ImageView) mouseEvent.getSource()).getImage() != null) {

                    if (playerOneTurn) {

                        Card card = (Card) ((ImageView) mouseEvent.getSource()).getUserData();
                        if (i == card.getValue().GetValue() && i > 0) {
                            playedCardsList.add(((ImageView) mouseEvent.getSource()));

                            for (ImageView i : playedCardsList) {
                                collectedCards++;
                                i.setImage(null);
                                i.setUserData(null);
                            }


                                MessageData messageData = new MessageData(MessageType.GAMEMOVE, moves);
                            System.out.println("Probaaaa" + messageData.getMoves());
                                sendGamaMoveDataToServer(messageData);


                            int numberOfPoints = playerOne.getPoints() + setCardPoints(card);
                            playerOne.setPoints(numberOfPoints);
                            mainGridPane.getChildren().remove(((ImageView) mouseEvent.getSource()));


                            ((ImageView) mouseEvent.getSource()).setUserData(null);


                            clear();
                            playerOneTurn = false;
                            playerOne.getPlayerCards().remove(card);
                            playerOne.setCardsCollected(collectedCards);
                            cardsOnTable.remove(card);
                            playerOneCardPicked.add(card);
                            System.out.println(playerOneCardPicked);
                            System.out.println("br pn: " + numberOfPoints + " br kk: " + playerOne.getCardsCollected());
                            recordGameMove(playerOne, card, playerOneCardPicked);
                        }
                    }
                }
            } catch (NullPointerException e) {
                e.getMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int setCardPoints(Card card) {
        if (card.getSuit().name().equalsIgnoreCase("clubs") && card.getValue().GetValue() == 2) {
            return 1;
        } else if (card.getSuit().name().equalsIgnoreCase("diamonds") && card.getValue().GetValue() == 10) {
            return 2;
        } else if (card.getValue().GetValue() == 11 || card.getValue().GetValue() == 12 || card.getValue().GetValue() == 13 || card.getValue().GetValue() == 14) {
            return 1;
        } else {
            return 0;
        }

    }

    private int getMaxCollectedCards() {
        return Math.max(playerOne.getCardsCollected(), playerTwo.getCardsCollected());
    }

    private void endGame() {
        if (getMaxCollectedCards() == playerOne.getCardsCollected()) {
            playerOne.setPoints(playerOne.getPoints() + 3);
        } else if (getMaxCollectedCards() == playerTwo.getCardsCollected()) {
            playerTwo.setPoints(playerTwo.getPoints() + 3);
        }
        System.out.println("END GAME cards on Table" + cardsOnTable);
        allScores.put(playerOne.getName(), playerOne.getPoints());
        allScores.put(playerTwo.getName(), playerTwo.getPoints());


        System.out.println(playerOne.getName() + playerOne.getPoints());
    }


    private void recordGameMove(Player player, Card card, List<Card> cardsPicked) {
        GameMove gm = new GameMove(player.getName(), card, cardsPicked, LocalDateTime.now());
        lastGameMoves.add(gm);
    }


}

