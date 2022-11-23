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

/**
 * The controller class for the auto generation window.
 */
public class AutoGenerationController implements Initializable {

    /**
     * The text field to store the input from the client for the averge k1 Enegry.
     */
    @FXML
    private TextField averageK1EnergyField;

    /**
     * The text field to store the input from the client for the averge k2 Enegry.
     */
    @FXML
    private TextField averageK2EnergyField;

    /**
     * The text field to store the input from the client for the number of students to be generated.
     */
    @FXML
    private TextField numberOfStudentsField;

    /**
     * The text field to store the input from the client for the probabilities of k3 tick 1 for all the students.
     */
    @FXML
    private TextField probK3Tick1Field;

    /**
     * The text field to store the input from the client for the probabilities of k3 tick 2 for all the students.
     */
    @FXML
    private TextField probK3Tick2Field;

    /**
     * The text field to store the input from the client for the probabilities of my prefrence is true for all the students.
     */
    @FXML
    private TextField probMyPreferenceTrue;

    /**
     * The sumbit button to confirm the input from the client.
     */
    @FXML
    private Button submitButton;

    /**
     * The property to store the created students.
     */
    Property<ArrayList<Person>> students = new SimpleObjectProperty<>();

    /**
     * @param event the button click event.
     */
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
        }

        event.consume();
    }

    /**
     * @param url            The location used to resolve relative paths for the root object, or 
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
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
    public boolean dataValidated() {
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
