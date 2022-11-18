package ATU;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;


import java.io.IOException;
import java.util.Objects;

public class LookUpScene extends Scene implements HasController<LookUpController> {
    
    private final LookUpController controller;

    public LookUpScene() throws IOException {
        super(new Label("Loading..."));
        final var fxml = getClass().getClassLoader().getResource("LookUp.fxml");
        final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
        final var startPane = loader.<Pane>load();
        this.setRoot(startPane);
        controller = loader.getController();
    }

    @Override
    public LookUpController getController() {
        return controller;
    }
}
