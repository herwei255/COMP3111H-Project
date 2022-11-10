package com.example.atu;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.*;

public class Library extends Application {

    private TableView<Statistics> stat_table = new TableView<Statistics>();
    private TableView<Person> person_table = new TableView<Person>();

    private final ObservableList<Statistics> stat_data = FXCollections.observableArrayList(
            new Statistics("Total Number of Students", "100"),
            new Statistics("K1_Energy(Average, Min, Max)", "(59.8, 10, 80)"),
            new Statistics("K2_Energy(Average, Min, Max)", "(62.3, 40, 85)"), new Statistics("K3_Tick1 = 1", "12"),
            new Statistics("K3_Tick2 = 1", "3"), new Statistics("My_Preference = 1", "19"));

    private final static ObservableList<Person> person_data = FXCollections.observableArrayList();

    public static final String delimiter = ",";

    public static void read(String csvFile) {

        System.out.print("\n");
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

    public static void main(String[] args) throws Exception {
        //get current directory
        String currentDir = System.getProperty("user.dir");
        String csvFile = currentDir + "//student_data.csv";
        
        Library.read(csvFile);
        System.out.println("Hello");
        launch(args);

    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Month");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
       
        lineChart.setTitle("Stock Monitoring, 2010");
                          
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Portfolio 1");
        
        series1.getData().add(new XYChart.Data("Jan", 23));
        series1.getData().add(new XYChart.Data("Feb", 14));
        series1.getData().add(new XYChart.Data("Mar", 15));
        series1.getData().add(new XYChart.Data("Apr", 24));
        series1.getData().add(new XYChart.Data("May", 34));
        series1.getData().add(new XYChart.Data("Jun", 36));
        series1.getData().add(new XYChart.Data("Jul", 22));
        series1.getData().add(new XYChart.Data("Aug", 45));
        series1.getData().add(new XYChart.Data("Sep", 43));
        series1.getData().add(new XYChart.Data("Oct", 17));
        series1.getData().add(new XYChart.Data("Nov", 29));
        series1.getData().add(new XYChart.Data("Dec", 25));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Portfolio 2");
        series2.getData().add(new XYChart.Data("Jan", 33));
        series2.getData().add(new XYChart.Data("Feb", 34));
        series2.getData().add(new XYChart.Data("Mar", 25));
        series2.getData().add(new XYChart.Data("Apr", 44));
        series2.getData().add(new XYChart.Data("May", 39));
        series2.getData().add(new XYChart.Data("Jun", 16));
        series2.getData().add(new XYChart.Data("Jul", 55));
        series2.getData().add(new XYChart.Data("Aug", 54));
        series2.getData().add(new XYChart.Data("Sep", 48));
        series2.getData().add(new XYChart.Data("Oct", 27));
        series2.getData().add(new XYChart.Data("Nov", 37));
        series2.getData().add(new XYChart.Data("Dec", 29));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Portfolio 3");
        series3.getData().add(new XYChart.Data("Jan", 44));
        series3.getData().add(new XYChart.Data("Feb", 35));
        series3.getData().add(new XYChart.Data("Mar", 36));
        series3.getData().add(new XYChart.Data("Apr", 33));
        series3.getData().add(new XYChart.Data("May", 31));
        series3.getData().add(new XYChart.Data("Jun", 26));
        series3.getData().add(new XYChart.Data("Jul", 22));
        series3.getData().add(new XYChart.Data("Aug", 25));
        series3.getData().add(new XYChart.Data("Sep", 43));
        series3.getData().add(new XYChart.Data("Oct", 44));
        series3.getData().add(new XYChart.Data("Nov", 45));
        series3.getData().add(new XYChart.Data("Dec", 44));
        
        Scene scene  = new Scene(lineChart,800,600);       
        lineChart.getData().addAll(series1, series2, series3);
       
        stage.setScene(scene);
        stage.show();
    }

    public static class Statistics {

        private final SimpleStringProperty entry;
        private final SimpleStringProperty value;

        private Statistics(String fName, String lName) {
            this.entry = new SimpleStringProperty(fName);
            this.value = new SimpleStringProperty(lName);
        }

        public String getEntry() {
            return entry.get();
        }

        public void setEntry(String val) {
            entry.set(val);
        }

        public String getValue() {
            return value.get();
        }

        public void setValue(String val) {
            value.set(val);
        }

    }

    public static class Person {

        private final SimpleStringProperty studentid;
        private final SimpleStringProperty studentname;
        private final SimpleStringProperty k1energy;
        private final SimpleStringProperty k2energy;
        private final SimpleStringProperty k3trick1;
        private final SimpleStringProperty k3trick2;
        private final SimpleStringProperty mypreference;
        private final SimpleStringProperty concerns;

        private Person(String student_id, String student_name, String k1_energy, String k2_energy, String k3_trick1,
                       String k3_trick2, String my_preference, String concerns) {
            this.studentid = new SimpleStringProperty(student_id);
            this.studentname = new SimpleStringProperty(student_name);
            this.k1energy = new SimpleStringProperty(k1_energy);
            this.k2energy = new SimpleStringProperty(k2_energy);
            this.k3trick1 = new SimpleStringProperty(k3_trick1);
            this.k3trick2 = new SimpleStringProperty(k3_trick2);
            this.mypreference = new SimpleStringProperty(my_preference);
            this.concerns = new SimpleStringProperty(concerns);
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