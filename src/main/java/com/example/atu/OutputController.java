package com.example.atu;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;

import com.example.atu.MainController.Person;

import java.net.URL;
import java.util.ResourceBundle;

public class OutputController implements Initializable {

    @FXML
    public LineChart<String, Number> LineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    List<Person> personList = new ArrayList<Person>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series k1Series = new XYChart.Series();
        XYChart.Series k2Series = new XYChart.Series();
    }

}
