package com.example.demo.controller;

import com.example.demo.SecondScreenViewController;
import com.example.demo.Utils.ReflectionUtils;
import com.example.demo.Utils.SceneUtil;
import com.example.demo.clientModel.GameStateList;
import com.example.demo.model.Card;
import com.example.demo.model.CellState;
import com.example.demo.clientModel.GameState;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class MenubarController {

    public static final String GAME_STATE_SER = "gameState.ser";
    public static final String CLASS = ".class";
    public static final String DOCUMENTATION_HTML = "documentation.html";

    public void showNewGameScreen() {
        SecondScreenViewController.lastGameMoves.clear();
        SceneUtil.setNewSceneToStage("view/second-screen-view.fxml", 900, 900, "Tablic!");
    }
    public void showHighScoresScreen() {
        SceneUtil.setNewSceneToStage("view/highscore_view.fxml", 600, 400, "High score!");
    }

    public void showGameMoves() {
        SceneUtil.setNewSceneToStage("view/gameMoves.fxml", 600, 400, "High score!");
    }

    public void saveGame() throws IOException {
        GameState gameState = new GameState();
        List<CellState> cellStateList = new ArrayList<>();
       for (int row = 0; row < SecondScreenViewController.NUMBER_OF_ROWS; row++) {
            for (int col = 0; col < SecondScreenViewController.NUMBER_OF_COLUMNS; col++) {
                if (SecondScreenViewController.board[row][col] !=null) {
                    CellState cellState = new CellState(row, col, (Card) SecondScreenViewController.board[row][col].getUserData());
                    cellStateList.add(cellState);
                }
            }
        }
        gameState.setCellStateList(cellStateList);
        gameState.setPlayerOneName(SecondScreenViewController.playerOne.getName());

        gameState.setPlayerOneCardsPicked(SecondScreenViewController.playerOneCardPicked);
        gameState.setPlayerTwoCardsPicked(SecondScreenViewController.playerTwoCardPicked);


        gameState.setPlayerOneCurrentCardsList(SecondScreenViewController.playerOne.getPlayerCards());
        gameState.setPlayerTwoCurrentCardsList(SecondScreenViewController.playerTwo.getPlayerCards());


        gameState.setPlayerOneTurn(SecondScreenViewController.playerOneTurn);

        gameState.setCardsOnTable(SecondScreenViewController.cardsOnTable);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GAME_STATE_SER))) {

            oos.writeObject(gameState);
        }
        GameStateList list = new GameStateList();
        list.gameStatesList.add(gameState);
        xmlWrite(list);
    }

    public void loadGame() throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GAME_STATE_SER))) {
            for (int row = 0; row < SecondScreenViewController.NUMBER_OF_ROWS; row++) {
                for (int col = 0; col < SecondScreenViewController.NUMBER_OF_COLUMNS; col++) {
                    if (SecondScreenViewController.board[row][col] != null) {
                        SecondScreenViewController.board[row][col].setImage(null);
                    }
                }
            }
            GameState gameState = (GameState) ois.readObject();
           for (CellState cellState : gameState.getCellStateList()) {
                if (SecondScreenViewController.board[cellState.getCoordinateX()][cellState.getCoordinateY()] != null) {
                    SecondScreenViewController.board[cellState.getCoordinateX()][cellState.getCoordinateY()].setUserData(cellState.getCardOnTable());
                    SecondScreenViewController.board[cellState.getCoordinateX()][cellState.getCoordinateY()].setImage(cellState.getCardOnTable().getCardImage());
                }
            }

            SecondScreenViewController.playerOne.setName(gameState.getPlayerOneName());
            SecondScreenViewController.playerOneCardPicked.addAll(gameState.getPlayerOneCardsPicked());
            SecondScreenViewController.playerTwoCardPicked.addAll(gameState.getPlayerTwoCardsPicked());

            SecondScreenViewController.playerOne.setPlayerCards(gameState.getPlayerOneCurrentCardsList());
            SecondScreenViewController.playerTwo.setPlayerCards(gameState.getPlayerTwoCurrentCardsList());

            SecondScreenViewController.playerOneTurn = gameState.getPlayerOneTurn();

            SecondScreenViewController.cardsOnTable = gameState.getCardsOnTable();
            setCards();
        }

    }

    final String IMAGE = "C:\\Users\\Jo\\Documents\\Svi predmeti\\5. PETI SEMESTAR\\JAVA2\\Tablic_Game\\src\\main\\resources\\images\\Back_of_card.png";

    private void setCards() {
        Image image = new Image(IMAGE);
        for (ImageView iv : SecondScreenViewController.playerOneCardPlaceholders) {
            iv.setImage(null);
        }
        for (int i = 0; i < SecondScreenViewController.playerOne.getPlayerCards().size(); i++)
            SecondScreenViewController.playerOneCardPlaceholders.get(i).setImage(SecondScreenViewController.playerOne.getPlayerCards().get(i).getCardImage());

        for (ImageView iv : SecondScreenViewController.playerTwoCardPlaceholders) {
            iv.setImage(null);
        }

        for (int i = 0; i < SecondScreenViewController.playerTwo.getPlayerCards().size(); i++) {
            SecondScreenViewController.playerTwoCardPlaceholders.get(i).setImage(image);
        }


    }
    public void generateDocumentation() {
        try {
            List<Path> filesList = Files.walk(Path.of(".")).filter(p -> p.getFileName().toString().endsWith(CLASS)).toList();
            filesList.forEach(System.out::println);
            List<Class> metaInfo = new ArrayList<>();
            for (Path path : filesList) {
                String fullQualifiedClassName = "";
                String[] paths = path.toString().split("\\\\");
                boolean startJoining = false;
                for (String segment : paths) {
                    if ("classes".equals(segment)) {
                        startJoining = true;
                        continue;
                    }
                    if (startJoining) {
                        if (segment.endsWith(CLASS)) {
                            fullQualifiedClassName += segment.substring(0, segment.lastIndexOf("."));
                        } else {
                            fullQualifiedClassName += segment + ".";
                        }
                    }
                }
                Class clazz = Class.forName(fullQualifiedClassName);
                metaInfo.add(clazz);
            }
            String documentationString = "";
            for (Class clazz : metaInfo) {
                documentationString += "<h2>" + clazz.getSimpleName() + "</h2>\n";
                documentationString += "<h3>  List of member variables: </h3\n>";
                for (Field f : clazz.getDeclaredFields()) {
                    documentationString += ReflectionUtils.retrieveModifiers(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName() + "<br />\n";
                }
                Constructor[] constructors = clazz.getConstructors();
                documentationString += "<h3>  List of constructors: </h3\n>";
                for (Constructor c : constructors) {

                    documentationString += "<h4>" + ReflectionUtils.retrieveModifiers(c.getModifiers()) + c.getName() + "(" + ReflectionUtils.retrieveParams(c.getParameters()) + ")" + "</h4>\n";
                }
                documentationString += "<h3> List of Methods: </h3>\n";
                for (Method method : clazz.getDeclaredMethods()){


                   documentationString += "<h4>"+ ReflectionUtils.retrieveModifiers(method.getModifiers()) +
                           method.getReturnType().getSimpleName() + " "
                           + method.getName() + "(" + ReflectionUtils.retrieveParams(method.getParameters()) + ")" +"</h4>";
                }
            }
            String content = "" + "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<title>Class Documentation</title>\n" + "</head>\n" + "<body>\n" + "\n" + "<h1>List of classes</h1>\n" + documentationString + "\n" + "</body>\n" + "</html>";
            Files.writeString(Path.of(DOCUMENTATION_HTML), content, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    public void xmlWrite(GameStateList gameStateList) {
        try {
            DocumentBuilderFactory documentBuilderFactory
                    = DocumentBuilderFactory.newInstance();


            DocumentBuilder documentBuilder
                    = documentBuilderFactory.newDocumentBuilder();

            Document xmlDocument = documentBuilder.newDocument();

            Element rootElement = xmlDocument.createElement("GameStates");

            xmlDocument.appendChild(rootElement);

            for(GameState gameState : gameStateList.gameStatesList) {
                Element gameStateElement
                        = xmlDocument.createElement("Gamestate");
                Node gameStateTextNode = xmlDocument.createTextNode(gameState.toString());
                gameStateElement.appendChild(gameStateTextNode);
                rootElement.appendChild(gameStateElement);
            }


            Transformer transformer
                    = TransformerFactory.newInstance().newTransformer();

            Source xmlSource = new DOMSource(xmlDocument);
            Result xmlResult = new StreamResult(new File("gameStates.xml"));

            transformer.transform(xmlSource, xmlResult);

            System.out.println("XML Created!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void xmlRead() {
        String readFromXML = "";

        try {
            File inputFile = new File("gameStates.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Gamestate");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    readFromXML += eElement.getTextContent();

                }
            }
            System.out.println(readFromXML);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
