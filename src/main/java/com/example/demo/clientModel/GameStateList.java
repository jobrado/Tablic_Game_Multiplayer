package com.example.demo.clientModel;

import java.io.Serializable;
import java.util.ArrayList;

public class GameStateList implements Serializable {
    public ArrayList<GameState> gameStatesList = new ArrayList<GameState>();
    public ArrayList<GameState> getGameStates() {
        return gameStatesList;
    }

}
