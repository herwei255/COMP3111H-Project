package ATU.controllers;

import java.util.Random;

import ATU.models.Person;
import ATU.utils.ATUEngine;
import ATU.scenes.LookUpScene;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LookUpControllerTest {

    private LookUpScene lookUpScene;
    private ATUEngine atuEngine;

    @BeforeEach
    void setUp() {
        try {
            lookUpScene = new LookUpScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testLookUpScene() {
        assertNotNull(lookUpScene);
    }

    @Test
    void testInitLookUp() {
        int num = 100;
        Person[] hundredPersons = new Person[100];

        for (int i = 0; i < num; i++) {           
            hundredPersons[i] = new Person("00000003", "Dose Liu", "doiseLIU92@connect.ust.hk", "70", "60", "1", "0", "0", "");
        }

        atuEngine = new ATUEngine(hundredPersons);
        Person[][] groups = atuEngine.createGroups();
        assertEquals(groups.length, 33);
        lookUpScene.getController().initLookUp(groups);

        lookUpScene.getController().serachButtonPressed(null);
    }
}