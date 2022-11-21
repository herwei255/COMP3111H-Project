package ATU.controllers;

import ATU.models.Person;
import ATU.scenes.OutputScene;

import java.io.*;
import java.util.Random;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.stage.Stage;

class OutputControllerTest {

    private Stage stage;
    private OutputScene outputScene;

    @BeforeEach
    void setUp() {
        try {
            outputScene = new OutputScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testOutputScene() {
        assertNotNull(outputScene);
    }
    
    @Test
    void testOutputInit() {
        int num = 100;
        Person[] hundredPersons = new Person[100];

        for (int i = 0; i < num; i++) {
            hundredPersons[i] = new Person("00000003", "Dose Liu", "doiseLIU92@connect.ust.hk", "70", "60", "1", "0", "0", "");
        }
        outputScene.getController().initOutput(hundredPersons);
    }

}