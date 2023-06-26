package com.example.demo.Utils;

import com.example.demo.HelloApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class SceneUtil {
    public static void setNewSceneToStage(String name, int v, int v1, String s) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(name));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), v, v1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        HelloApplication.getMainStage().setTitle(s);
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
}
