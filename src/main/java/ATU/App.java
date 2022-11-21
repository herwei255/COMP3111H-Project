package ATU;

import ATU.scenes.MainScene;
import ATU.models.Person;
import ATU.scenes.OutputScene;
import ATU.scenes.AutoGenerationScene;
import ATU.scenes.LookUpScene;
import javafx.application.Application;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Random;

/**
 * The JavaFX application that launches the ATU Engine.
 */
public class App extends Application {

    private Stage stage;
    private MainScene mainScene;
    private OutputScene outputScene;
    private AutoGenerationScene autoGenerationScene;
    private LookUpScene lookUpScene;

    public MainScene getMainScene() {
        return mainScene;
    }

    @Override
    public void start(Stage stage) throws IOException {
        mainScene = new MainScene();
        stage.setTitle("Auto Team Up Engine");
        stage.setMaximized(true);
        this.stage = stage;
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getStage() {
        return stage;
    }
}
