package ATU.controllers;

import ATU.models.Person;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;

/**
 * The controller class for the Look Up window.
 */
public class LookUpController{

    /**
     * The warning label text to display the warning message.
     */
    @FXML
    private Label warning;

    /**
     * The grid pane for displaying the results.
     */
    @FXML
    private GridPane lookUpTable;

    /**
     * The label to show the k1 energy of the found student.
     */
    @FXML
    private Label k1Avg;

    /**
     * The label to show the k2 energy of the found student.
     */
    @FXML
    private Label k2Avg;

    /**
     * The label to show the id of the found student.
     */
    @FXML
    private Label myID;

    /**
     * The label to show the full name of the found student.
     */
    @FXML
    private Label myName;

    /**
     * The textfield to save the student id input from the client.
     */
    @FXML
    private TextField studentIDSearchBox;

    /**
     * The textfield to save the student name input from the client.
     */
    @FXML
    private TextField studentIDSearchBox1;

    /**
     * The label to show the found student's first teammate's name.
     */
    @FXML
    private Label teammateOneName;

    /**
     * The label to show the found student's third teammate's name.
     */
    @FXML
    private Label teammateThreeName;

    /**
     * The label to show the found student's second teammate's name.
     */
    @FXML
    private Label teammateTwoName;

    /**
     * The label to show the team numbr of the found student's fourth teammate's name.
     */
    @FXML
    private Label teamNumber;

    /**
     * The array to store the grouped students.
     */
    private Person[][] groupedArr;

    /**
     * @param event The button event that triggers the method.
     */
    @FXML
    void serachButtonPressed(ActionEvent event) {
        System.out.println("Search button pressed");
        Boolean found = false;
        int foundInstance = 0;
        String id = studentIDSearchBox.getText();
        String name = studentIDSearchBox1.getText();
        warning.setOpacity(0);
        lookUpTable.setOpacity(0);
        if(id.equals("") && name.equals("")){
            warning.setOpacity(1);
            warning.setText("Please enter a student ID or name");
        }
        else {
            for(int i = 0; i < groupedArr.length; i++){
                for(int j = 0; j < groupedArr[i].length; j++){
                    if (groupedArr[i][j] == null) {
                        continue;
                    }
                    if(groupedArr[i][j].getStudentid().contains(id) && groupedArr[i][j].getStudentname().toLowerCase().contains(name.toLowerCase())){
                        found = true;
                        foundInstance++;
                        lookUpTable.setOpacity(1);
                        myID.setText(groupedArr[i][j].getStudentid());
                        myName.setText(groupedArr[i][j].getStudentname());
                        teamNumber.setText(String.valueOf(i+1));
                        if(j == 0) {
                            teammateOneName.setText(groupedArr[i][j+1].getStudentname());
                            teammateTwoName.setText(groupedArr[i][j+2].getStudentname());
                            if (groupedArr[i][j+3] != null) {
                                teammateThreeName.setText(groupedArr[i][j+3].getStudentname());
                            } else {
                                teammateThreeName.setText("N/A");
                            }
                        } else if(j == 1) {
                            teammateOneName.setText(groupedArr[i][j+1].getStudentname());
                            if (groupedArr[i][j+2] != null) {
                                teammateThreeName.setText(groupedArr[i][j+2].getStudentname());
                            } else {
                                teammateTwoName.setText(groupedArr[i][j-1].getStudentname());
                                teammateThreeName.setText("N/A");
                            }
                        }else if (j ==2) {
                            if (groupedArr[i][j+1] != null) {
                                teammateThreeName.setText(groupedArr[i][j+1].getStudentname());
                            } else {
                                teammateThreeName.setText("N/A");
                            }
                            teammateOneName.setText(groupedArr[i][j-1].getStudentname());
                            teammateTwoName.setText(groupedArr[i][j-2].getStudentname());
                        } else {
                            teammateOneName.setText(groupedArr[i][j-1].getStudentname());
                            teammateTwoName.setText(groupedArr[i][j-2].getStudentname());
                            teammateThreeName.setText(groupedArr[i][j-3].getStudentname());
                        } 
                        float k1Avg = 0;
                        float k2Avg = 0;
                        int personCount = 0;
                        for(int k = 0; k < groupedArr[i].length; k++){
                            if (groupedArr[i][k] == null){
                                continue;
                            }
                            k1Avg += Integer.parseInt(groupedArr[i][k].getK1energy());
                            k2Avg += Integer.parseInt(groupedArr[i][k].getK2energy());
                            personCount++;
                        }
                        k1Avg = (float) k1Avg / personCount;
                        k2Avg = (float) k2Avg / personCount;
                        String k1AvgString = String.format("%.2f", k1Avg);
                        String k2AvgString = String.format("%.2f", k2Avg);
                        this.k1Avg.setText(k1AvgString);
                        this.k2Avg.setText(k2AvgString);
                    }
                }
            }
        }
        if (!found) {
            warning.setOpacity(1);
            lookUpTable.setOpacity(0);
            warning.setText("Student Not Found! \nPlease input correct student name or student ID");
        }
        if (foundInstance > 1) {
            warning.setOpacity(1);
            lookUpTable.setOpacity(0);
            warning.setText("Multiple Students Found! \nPlease input more specific student name or student ID");
        }
    }

    /**
     * @param groupedArr Initialize the array to store the grouped students.
     */
    public void initLookUp(Person[][] groupedArr) {
        this.groupedArr = groupedArr;
        lookUpTable.setOpacity(0);
        warning.setOpacity(0);
    }
}
