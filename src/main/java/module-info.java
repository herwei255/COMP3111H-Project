module ATU {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ATU to javafx.fxml;
    exports ATU;
    exports ATU.models;
    exports ATU.utils;
    opens ATU.utils to javafx.fxml;
    exports ATU.controllers;
    opens ATU.controllers to javafx.fxml;
    exports ATU.scenes;
    opens ATU.scenes to javafx.fxml;
}