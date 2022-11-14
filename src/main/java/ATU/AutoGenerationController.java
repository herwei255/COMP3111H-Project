package ATU;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
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

    Property<ArrayList<MainController.Person>> students = new SimpleObjectProperty<>();

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
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        }

        event.consume();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        forceFieldsNumeric();
    }

    /**
     *  Force the fields to be numeric only
     */
    private void forceFieldsNumeric() {
        // TextField[] fields = new TextField[averageK1EnergyField, averageK2EnergyField, numberOfStudentsField, probK3Tick1Field, probK3Tick2Field, probMyPreferenceTrue];
        numberOfStudentsField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numberOfStudentsField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    boolean dataValidated() {
        try {
            int noOfStudents = Integer.parseInt(numberOfStudentsField.getText());
            if (!(noOfStudents > 0 && noOfStudents <= 900)) {
                return false;
            }

            int avgK1Energy = Integer.parseInt(averageK1EnergyField.getText());
            if (!(avgK1Energy > 0 && avgK1Energy <= 100)) {
                return false;
            }

            int avgK2Energy = Integer.parseInt(averageK2EnergyField.getText());
            if (!(avgK2Energy > 0 && avgK2Energy <= 100)) {
                return false;
            }

            int K3Tick1 = Integer.parseInt(probK3Tick1Field.getText());
            if (!(avgK1Energy > 0 && avgK1Energy <= 100)) {
                return false;
            }

            int K3Tick2 = Integer.parseInt(probK3Tick2Field.getText());
            if (!(avgK2Energy > 0 && avgK2Energy <= 100)) {
                return false;
            }

            int myPref = Integer.parseInt(probMyPreferenceTrue.getText());
            if (!(myPref > 0 && myPref <= 100)) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }

    }



}
