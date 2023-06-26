package com.example.demo.clientModel;

import java.io.Serializable;
import java.util.List;

public class MessageData implements Serializable {
    private MessageType type;
    private PlayerData playerData;
    private GameState gameState;
    private StartGameData startGameData;
    private Move move;
    private String start;
    private List<Move> moves;

    public MessageData(MessageType type, List<Move> moves) {
        this.type = type;
        this.moves = moves;
    }

    public MessageData(MessageType type, String start) {
        this.type = type;
        this.start = start;
    }

    public MessageData(MessageType type, Move move) {
        this.type = type;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public StartGameData getStartGameData() {
        return startGameData;
    }

    public void setStartGameData(StartGameData startGameData) {
        this.startGameData = startGameData;
    }

    public MessageData(MessageType type, StartGameData startGameData) {
        this.type = type;
        this.startGameData = startGameData;
    }

    public MessageData(MessageType type, PlayerData playerData) {
        this.type = type;
        this.playerData = playerData;
    }

    public MessageData(MessageType type, GameState gameState) {
        this.type = type;
        this.gameState = gameState;
    }


    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public void setPlayerData(PlayerData playerData) {
        this.playerData = playerData;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Move> getMoves() {
        return moves;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "type=" + type +
                ", playerData=" + playerData +
                ", gameState=" + gameState +
                ", startGameData=" + startGameData +
                ", gameMove=" + move +
                '}';
    }
}
