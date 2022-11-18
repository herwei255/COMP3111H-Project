package ATU;

import ATU.scenes.MainScene;
import ATU.scenes.OutputScene;
import javafx.application.Application;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * The JavaFX application that launches the ATU Engine.
 */
public class App extends Application {

    private Stage stage;
    private MainScene mainScene;
    private OutputScene outputScene;

    public MainScene getMainScene() {
        return mainScene;
    }

    @Override
    public void start(Stage stage) throws IOException {
        MainScene scene = new MainScene();
        this.mainScene = scene;
        stage.setTitle("Auto Team Up Engine");
        stage.setMaximized(true);
        this.stage = stage;
        stage.setScene(scene);
        stage.show();

        // startScene.getRoot().addEventFilter(MapEvent.OPEN_MAP_EVENT_TYPE, this::onOpenMap);

    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getStage() {
        return stage;
    }
}
