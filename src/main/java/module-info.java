module com.example.atu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.atu to javafx.fxml;
    exports com.example.atu;
}