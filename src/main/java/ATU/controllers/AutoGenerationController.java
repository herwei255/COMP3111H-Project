package ATU.controllers;

import ATU.utils.StudentAutoGenerator;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ATU.models.Person;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AutoGenerationController implements Initializable {

    @FXML
    private TextField averageK1EnergyField;

    @FXML
    private TextField averageK2EnergyField;

    @FXML
    private TextField numberOfStudentsField;

    @FXML
    private TextField probK3Tick1Field;

    @FXML
    private TextField probK3Tick2Field;

    @FXML
    private TextField probMyPreferenceTrue;

    @FXML
    private Button submitButton;

    Property<ArrayList<Person>> students = new SimpleObjectProperty<>();

    @FXML
    void submitButtonClicked(ActionEvent event) {
        if (dataValidated()) {
            students.setValue(
                    StudentAutoGenerator.Generate(new StudentAutoGenerator.StudentGeneratorStatistics(
                            Integer.parseInt(numberOfStudentsField.getText()),
                            Integer.parseInt(averageK1EnergyField.getText()),
                            Integer.parseInt(averageK2EnergyField.getText()),
                            Integer.parseInt(probK3Tick1Field.getText()),
                            Integer.parseInt(probK3Tick2Field.getText()),
                            Integer.parseInt(probMyPreferenceTrue.getText())
                    ))
            );

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        } else {
            // Not validated
            // Node node = (Node) event.getSource();
            // Stage stage = (Stage) node.getScene().getWindow();
            // stage.close();
        }

        event.consume();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        forceFieldsNumeric();
    }

    /**
     *  Force the fields to be numeric only, i.e. only input of digits will be accepted.
     */
    private void forceFieldsNumeric() {
        numberOfStudentsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numberOfStudentsField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        averageK1EnergyField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                averageK1EnergyField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        averageK2EnergyField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                averageK2EnergyField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        probK3Tick1Field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                probK3Tick1Field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        probK3Tick2Field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                probK3Tick2Field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        probMyPreferenceTrue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                probMyPreferenceTrue.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    /**
     * Validation checks for text fields in Auto Generation scene.
     *
     * @return true if all fields are within set range, and not null.
     */
    boolean dataValidated() {
        boolean noStudentsBool = true, avgK1Bool = true, avgK2Bool = true, k3Tick1Bool = true, k3Tick2Bool = true, myPrefBool = true;
        numberOfStudentsField.setStyle("-fx-text-fill: black ;");
        averageK1EnergyField.setStyle("-fx-text-fill: black ;");
        averageK2EnergyField.setStyle("-fx-text-fill: black ;");
        probK3Tick1Field.setStyle("-fx-text-fill: black ;");
        probK3Tick2Field.setStyle("-fx-text-fill: black ;");
        probMyPreferenceTrue.setStyle("-fx-text-fill: black ;");

        try {
            int noOfStudents = Integer.parseInt(numberOfStudentsField.getText());
            if (!(noOfStudents > 0 && noOfStudents <= 999)) {
                numberOfStudentsField.setStyle("-fx-text-fill: red ;");
                noStudentsBool = false;
            }
        } catch (Exception e) {
            numberOfStudentsField.setStyle("-fx-text-fill: red ;");
            noStudentsBool = false;
        }
        try {
            int avgK1Energy = Integer.parseInt(averageK1EnergyField.getText());
            if (!(avgK1Energy > 0 && avgK1Energy <= 100)) {
                averageK1EnergyField.setStyle("-fx-text-fill: red ;");
                avgK1Bool = false;
            }
        } catch (Exception e) {
            averageK1EnergyField.setStyle("-fx-text-fill: red ;");
            avgK1Bool = false;
        }
        try {
            int avgK2Energy = Integer.parseInt(averageK2EnergyField.getText());
            if (!(avgK2Energy > 0 && avgK2Energy <= 100)) {
                averageK2EnergyField.setStyle("-fx-text-fill: red ;");
                avgK2Bool = false;
            }
        } catch (Exception e) {
            averageK2EnergyField.setStyle("-fx-text-fill: red ;");
            avgK2Bool = false;
        }
        try {
            int K3Tick1 = Integer.parseInt(probK3Tick1Field.getText());
            if (!(K3Tick1 > 0 && K3Tick1 <= 100)) {
                probK3Tick1Field.setStyle("-fx-text-fill: red ;");
                k3Tick1Bool = false;
            }
        } catch (Exception e) {
            probK3Tick1Field.setStyle("-fx-text-fill: red ;");
            k3Tick1Bool = false;
        }
        try {
            int K3Tick2 = Integer.parseInt(probK3Tick2Field.getText());
            if (!(K3Tick2 > 0 && K3Tick2 <= 100)) {
                probK3Tick2Field.setStyle("-fx-text-fill: red ;");
                k3Tick2Bool = false;
            }
        } catch (Exception e) {
            probK3Tick2Field.setStyle("-fx-text-fill: red ;");
            k3Tick2Bool = false;
        }
        try {
            int myPref = Integer.parseInt(probMyPreferenceTrue.getText());
            if (!(myPref > 0 && myPref <= 100)) {
                probMyPreferenceTrue.setStyle("-fx-text-fill: red ;");
                myPrefBool = false;
            }
        } catch (Exception e) {
            probMyPreferenceTrue.setStyle("-fx-text-fill: red ;");
            myPrefBool = false;
        }

        if (noStudentsBool && avgK1Bool && avgK2Bool && k3Tick1Bool && k3Tick2Bool && myPrefBool) {
            return true;
        }

        return false;
    }



}
