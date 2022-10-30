package com.example.atu;

// import com.example.atu.models.Person;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {



    @FXML
    private TableView<Person> studentTable;

    @FXML
    private Label totalNumberStudentsLabel;

    @FXML
    private Label K1EnergyLabel;

    @FXML
    private Label K2EnergyLabel;

    @FXML
    private Label K3Tick1Label;

    @FXML
    private Label K3Tick2Label;

    @FXML
    private Label MyPreferenceLabel;

    @FXML
    private Button autoGenerateDataButton;



    @FXML
    void autoGenerateDataButtonPressed(ActionEvent event) {

    }

    private final static ObservableList<Person> person_data = FXCollections.observableArrayList();

    public static final String delimiter = ",";



    /**
     * Initialize the controller.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization
        String csvFile = "/Users/hw/HKUST/2022-Y3a-Fall/COMP 3111H/Project/COMP3111H-Project/student_data.csv";
        read(csvFile);
        initializeTable();
        initializeStats();


        // Set listeners


    }

    /**
     * Read CSV data into and save the student fields into {@link person_data}.
     *
     * @param csvFile The CSV file.
     */
    public static void read(String csvFile) {

        // System.out.print("\n");
        try {
            File file = new File(csvFile);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = " ";
            String[] tempArr;
            br.readLine(); // skip the first line
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                person_data.add(new Person(tempArr[0], tempArr[1], tempArr[2], tempArr[3], tempArr[4], tempArr[5],
                        tempArr[6], tempArr[7]));
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Initialize {@link studentTable} with fields from {@link person_data}.
     */
    void initializeTable() {
        studentTable.setEditable(true);
        studentTable.setItems(person_data);
        // studentTable.refresh();

        var cols = studentTable.getColumns();
        cols.get(0).setCellValueFactory(new PropertyValueFactory("index"));
        cols.get(1).setCellValueFactory(new PropertyValueFactory("studentid"));
        cols.get(2).setCellValueFactory(new PropertyValueFactory("studentname"));
        // cols.get(3).setCellValueFactory(new PropertyValueFactory("k1energy"));
        cols.get(4).setCellValueFactory(new PropertyValueFactory("k1energy"));
        cols.get(5).setCellValueFactory(new PropertyValueFactory("k2energy"));
        cols.get(6).setCellValueFactory(new PropertyValueFactory("k3trick1"));
        cols.get(7).setCellValueFactory(new PropertyValueFactory("k3trick2"));
        cols.get(8).setCellValueFactory(new PropertyValueFactory("mypreference"));
        cols.get(9).setCellValueFactory(new PropertyValueFactory("concerns"));

        studentTable.refresh();





    }

    void initializeStats() {

        totalNumberStudentsLabel.setText(String.valueOf(Person.studentIndex));

        MyPreferenceLabel.setText();
        K3Tick2Label.setText();
        K3Tick1Label.setText();
        K2EnergyLabel.setText();
        K1EnergyLabel.setText();
        autoGenerateDataButton.setDisable(false);
    }

    public static class Person {
        private static Integer studentIndex = 0;
        private final SimpleStringProperty index;
        private final SimpleStringProperty studentid;
        private final SimpleStringProperty studentname;
        private final SimpleStringProperty k1energy;
        private final SimpleStringProperty k2energy;
        private final SimpleStringProperty k3trick1;
        private final SimpleStringProperty k3trick2;
        private final SimpleStringProperty mypreference;
        private final SimpleStringProperty concerns;
        private SimpleStringProperty groupID;

        public Person(String student_id, String student_name, String k1_energy, String k2_energy, String k3_trick1,
                      String k3_trick2, String my_preference, String concerns) {
            this.index = new SimpleStringProperty((studentIndex++).toString());
            this.studentid = new SimpleStringProperty(student_id);
            this.studentname = new SimpleStringProperty(student_name);
            this.k1energy = new SimpleStringProperty(k1_energy);
            this.k2energy = new SimpleStringProperty(k2_energy);
            this.k3trick1 = new SimpleStringProperty(k3_trick1);
            this.k3trick2 = new SimpleStringProperty(k3_trick2);
            this.mypreference = new SimpleStringProperty(my_preference);
            this.concerns = new SimpleStringProperty(concerns);
        }

        public String getGroupID() {
            return groupID.get();
        }

        public SimpleStringProperty groupIDProperty() {
            return groupID;
        }

        public void setGroupID(String groupID) {
            this.groupID.set(groupID);
        }

        public String getIndex() {
            return index.get();
        }

        public void setIndex(String index) {
            this.index.set(index);
        }

        public String getStudentid() {
            return studentid.get();
        }

        public void setStudentid(String val) {
            studentid.set(val);
        }

        public String getStudentname() {
            return studentname.get();
        }

        public void setStudentname(String val) {
            studentname.set(val);
        }

        public String getK1energy() {
            return k1energy.get();
        }

        public void setK1energy(String val) {
            k1energy.set(val);
        }

        public String getK2energy() {
            return k2energy.get();
        }

        public void setK2energy(String val) {
            k2energy.set(val);
        }

        public String getK3trick1() {
            return k3trick1.get();
        }

        public void setK3trick1(String val) {
            k3trick1.set(val);
        }

        public String getK3trick2() {
            return k3trick2.get();
        }

        public void setK3trick2(String val) {
            k3trick2.set(val);
        }

        public String getMypreference() {
            return mypreference.get();
        }

        public void setMypreference(String val) {
            mypreference.set(val);
        }

        public String getConcerns() {
            return concerns.get();
        }

        public void setConcerns(String val) {
            concerns.set(val);
        }

    }

}
