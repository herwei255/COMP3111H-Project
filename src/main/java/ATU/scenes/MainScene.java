package ATU.scenes;

import ATU.controllers.HasController;
import ATU.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class MainScene extends Scene implements HasController<MainController> {
    private final MainController controller;

    public MainScene() throws IOException {
        super(new Label("Loading..."));
        final var fxml = getClass().getClassLoader().getResource("main.fxml");
        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        System.out.println("AFter null");
        final var startPane = loader.<Pane>load();
        this.setRoot(startPane);
        controller = loader.getController();
    }

    @Override
    public MainController getController() {
        return controller;
    }
}
