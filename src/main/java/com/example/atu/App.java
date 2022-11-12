package com.example.atu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;



import java.io.IOException;

/**
 * The JavaFX application that launches the ATU Engine.
 */
public class App extends Application {

    private Stage stage;
    private MainScene mainScene;
    private OutputScene outputScene;

    @Override
    public void start(Stage stage) throws IOException {
        MainScene scene = new MainScene();
        this.mainScene = scene;
        stage.setTitle("Auto Team Up Engine");
        stage.setMaximized(true);
        this.stage = stage;
        stage.setScene(scene);
        stage.show();

        //outputscene
        // OutputScene outputScene = new OutputScene();
        // this.outputScene = outputScene;
        // stage.setTitle("Output Scene");
        // // stage.setMaximized(true);
        // this.stage = stage;
        // stage.setScene(outputScene);
        // stage.show();

        
        // startScene.getRoot().addEventFilter(MapEvent.OPEN_MAP_EVENT_TYPE, this::onOpenMap);

    }

    public static void main(String[] args) {
        launch();
    }
}
