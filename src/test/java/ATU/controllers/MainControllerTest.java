package ATU.controllers;

import ATU.scenes.MainScene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.*;

import javafx.event.ActionEvent;

class MainControllerTest {

    private MainScene mainScene;
    private Stage stage;

    @BeforeEach
    void setUp() {
        try {
            mainScene = new MainScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testMainScene() {
        assertNotNull(mainScene);
        assertNotNull(mainScene.getController());
    }   

}