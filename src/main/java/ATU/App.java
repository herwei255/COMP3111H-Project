package ATU;

import ATU.scenes.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The JavaFX application that launches the ATU Engine.
 */
public class App extends Application {

    /**
     *  Represents the main stage of the application
     */
    private Stage stage;

    /**
     *  Represents the main scene of the application
     */
    private MainScene mainScene;

    /**
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws IOException if the main scene cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        mainScene = new MainScene();
        stage.setTitle("Auto Team Up Engine");
        stage.setMaximized(true);
        this.stage = stage;
        stage.setScene(mainScene);
        stage.show();
    }

    /**
     * @return Return the main scene of the application
     */
    public MainScene getMainScene() {
        return mainScene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * @return Return the current stage for the app
     */
    public Stage getStage() {
        return stage;
    }
}
