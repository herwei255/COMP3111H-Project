module ATU {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ATU to javafx.fxml;
    exports ATU;
    exports ATU.models;
}