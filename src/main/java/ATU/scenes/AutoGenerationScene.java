package ATU.scenes;

import ATU.controllers.AutoGenerationController;
import ATU.controllers.HasController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

/**
 * The class for the AutoGenerationScene
 * Initializes the AutoGenerationScene from the respective fxml file
 * and provides the access to the controller for the scene
 */
public class AutoGenerationScene extends Scene implements HasController<AutoGenerationController> {
    
    /**
     * Represents the controller for the AutoGenerationScene
     */
    private final AutoGenerationController controller;

    /**
     * The constructor for the AutoGenerationScene
     * Initializing the AutoGenerationScene from the respective fxml file and initializing the controller
     * @throws IOException if the fxml file cannot be loaded or the auto generation scene class cannot be created
    */
    public AutoGenerationScene() throws IOException {
        super(new Label("Loading..."));
        final var fxml = getClass().getClassLoader().getResource("AutoGeneration.fxml");
        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        final var startPane = loader.<Pane>load();
        this.setRoot(startPane);
        controller = loader.getController();
    }

    /**
     * @return Return the controller for the AutoGenerationScene
     */
    @Override
    public AutoGenerationController getController() {
        return controller;
    }
}
