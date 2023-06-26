package com.example.demo.controller;

import com.example.demo.SecondScreenViewController;
import com.example.demo.model.GameMove;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class GameMovesController {
    @FXML
    private TextArea gameMovesTextArea;

    public void initialize() {
        for(GameMove gameMove : SecondScreenViewController.lastGameMoves) {
            gameMovesTextArea.appendText(gameMove.toString() + "\n");
        }
    }
}
