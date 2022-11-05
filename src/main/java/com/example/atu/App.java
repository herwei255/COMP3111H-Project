package com.example.atu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The JavaFX application that launches the ATU Engine.
 */
public class App extends Application {

    private Stage stage;
    private MainScene mainScene;

    @Override
    public void start(Stage stage) throws IOException {
        MainScene scene = new MainScene();
        this.mainScene = scene;
        stage.setTitle("Auto Team Up Engine");
        stage.setMaximized(true);
        this.stage = stage;
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
