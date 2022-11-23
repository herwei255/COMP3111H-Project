package ATU.scenes;

import ATU.controllers.HasController;
import ATU.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

/**
 * The class for the MainScene
 * Initializes the MainScene from the respective fxml file
 * and provides the access to the controller for the scene
 */
public class MainScene extends Scene implements HasController<MainController> {
    /**
     * Represents the controller for the MainScene
     */
    private final MainController controller;

    /**
     * The constructor for the MainScene
     * Initializing the MainScene from the respective fxml file and initializing the controller
     * @throws IOException if the fxml file cannot be loaded or the main scene class cannot be created
     */
    public MainScene() throws IOException {
        super(new Label("Loading..."));
        final var fxml = getClass().getClassLoader().getResource("main.fxml");
        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        System.out.println("AFter null");
        final var startPane = loader.<Pane>load();
        this.setRoot(startPane);
        controller = loader.getController();
    }

    /**
     * @return Return the controller for the MainScene
     */
    @Override
    public MainController getController() {
        return controller;
    }
}
