package ATU.scenes;

import ATU.controllers.HasController;
import ATU.controllers.OutputController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;


import java.io.IOException;
import java.util.Objects;

/**
 * The class for the OutputScene
 * Initializes the OutputScene from the respective fxml file
 * and provides the access to the controller for the scene
 */
public class OutputScene extends Scene implements HasController<OutputController> {

    /**
     * Represents the controller for the OutputScene
     */
    private final OutputController controller;

    /**
     * The constructor for the OutputScene
     * Initializing the OutputScene from the respective fxml file and initializing the controller
     * @throws IOException if the fxml file cannot be loaded or the output scene class cannot be created
     */
    public OutputScene() throws IOException {
        super(new Label("Loading..."));
        final var fxml = getClass().getClassLoader().getResource("output.fxml");
        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        final var startPane = loader.<Pane>load();
        this.setRoot(startPane);
        controller = loader.getController();
    }

    /**
     * @return Return the controller for the OutputScene
     */
    @Override
    public OutputController getController() {
        return controller;
    }
}
