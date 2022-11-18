package ATU.scenes;

import ATU.controllers.HasController;
import ATU.controllers.OutputController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;


import java.io.IOException;
import java.util.Objects;

public class OutputScene extends Scene implements HasController<OutputController> {
    
    private final OutputController controller;

    public OutputScene() throws IOException {
        super(new Label("Loading..."));
        final var fxml = getClass().getClassLoader().getResource("output.fxml");
        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        final var startPane = loader.<Pane>load();
        this.setRoot(startPane);
        controller = loader.getController();
    }

    @Override
    public OutputController getController() {
        return controller;
    }
}
