package com.example.demo;


import com.example.demo.Utils.SceneUtil;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static  Stage mainStage;

    private static String playerName;

    public static String getPlayerName() {
        return playerName;
    }

    @Override
    public void start(Stage stage) throws IOException {

        mainStage = stage;
        SceneUtil.setNewSceneToStage("view/first_screen_view.fxml",600,400, "Tablic!" );
    }
    public static Stage getMainStage(){
        return mainStage;
    }
    public static void main(String[] args) {
        String tempPlayerName = " ";
        Integer tokenNumber=0;
        for (String param : args){
            System.out.println("Next param" + param);
            if (args.length > tokenNumber) {
                tempPlayerName += param +" ";
            } else{
                tempPlayerName +=param;
            }
            tokenNumber++;

        }
        playerName = tempPlayerName;
        launch();
    }
}