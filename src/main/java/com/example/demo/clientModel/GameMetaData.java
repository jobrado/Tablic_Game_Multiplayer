package com.example.demo.clientModel;

public class GameMetaData {
    private PlayerData playerOneData;
    private PlayerData PlayerTwoData;

    public GameMetaData(PlayerData playerOneData, PlayerData playerTwoData) {
        this.playerOneData = playerOneData;
        PlayerTwoData = playerTwoData;
    }

    public PlayerData getPlayerOneData() {
        return playerOneData;
    }

    public void setPlayerOneData(PlayerData playerOneData) {
        this.playerOneData = playerOneData;
    }

    public PlayerData getPlayerTwoData() {
        return PlayerTwoData;
    }

    public void setPlayerTwoData(PlayerData playerTwoData) {
        PlayerTwoData = playerTwoData;
    }
}
