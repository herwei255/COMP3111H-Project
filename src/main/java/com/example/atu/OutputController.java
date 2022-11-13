package com.example.atu;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import com.example.atu.MainController.Person;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.List;
public class OutputController  {

    @FXML
    private LineChart<String, Number> LineChart;

    @FXML
    private LineChart<String, Number> LineChart1;

    @FXML
    private CategoryAxis x;

    @FXML
    private CategoryAxis x1;

    @FXML
    private NumberAxis y;

    @FXML
    private NumberAxis y1;
    //make a person array
    private Person[] personArr;
    
    //inititalize the person list
    public void initOutput(Person[] personArr){
        //deep copy personArr into 
        this.personArr = new Person[personArr.length];
        for (int i = 0; i < personArr.length; i++) {
            this.personArr[i] = new Person(personArr[i].getStudentid(), personArr[i].getStudentname(), personArr[i].getEmail(), personArr[i].getK1energy(), personArr[i].getK2energy(), personArr[i].getK3trick1(), personArr[i].getK3trick2(), personArr[i].getMypreference(), personArr[i].getConcerns());
        }

        //sort the person array by desending k1
        for (int i = 0; i < personArr.length; i++) {
            for (int j = 0; j < personArr.length - 1; j++) {
                if (Integer.parseInt(personArr[j].getK1energy()) < Integer.parseInt(personArr[j + 1].getK1energy())) {
                    Person temp = personArr[j];
                    personArr[j] = personArr[j + 1];
                    personArr[j + 1] = temp;
                }
            }
        }
        
        plotStudentKeyEnergy(personArr);

        //create ATUengine
        ATUEngine atuEngine = new ATUEngine(personArr);
        Person [][] groups = atuEngine.createGroups();
        plotTeamAverage(groups);
    }
    
    void plotStudentKeyEnergy(Person[] persons) {
        //create serires
        XYChart.Series k1Series = new XYChart.Series();
        XYChart.Series k2Series = new XYChart.Series();
        k1Series.setName("K1 Energy");
        k2Series.setName("K2 Energy");
        LineChart.setCreateSymbols(false);

        //add the data to the chart
        for (int i = 0; i < persons.length; i++) {
            Person p = persons[i];
            k1Series.getData().add(new XYChart.Data(String.valueOf(i+1), Integer.parseInt(p.getK1energy())));
            k2Series.getData().add(new XYChart.Data(String.valueOf(i+1), Integer.parseInt(p.getK2energy())));
        }
        LineChart.getData().addAll(k1Series, k2Series);
    }

    void plotTeamAverage(Person[][] groups) {
        //create a k1 and k2, average, array list
        XYChart.Series k1AvgSeries = new XYChart.Series();
        XYChart.Series k2AvgSeries = new XYChart.Series();
        XYChart.Series k1k2AvgSeries = new XYChart.Series();

        //print out groups
        for (int i = 0; i < groups.length; i++) {
            int group = i + 1;
            System.out.println("Group " + group);
            int k1_sum = 0;
            int k2_sum = 0;
            for (int j = 0; j < groups[i].length; j++) {
                //calculate the average energy level of each group
                k1_sum += Integer.parseInt(groups[i][j].getK1energy());
                k2_sum += Integer.parseInt(groups[i][j].getK2energy());
                
                //print the student id
                System.out.println(groups[i][j].getStudentid());
            }
            float k1_avg =  (float) k1_sum / groups[i].length;
            float k2_avg =  (float) k2_sum / groups[i].length;
            float k1k2Avg = (k1_avg + k2_avg) / 2;
            k1AvgSeries.getData().add(new XYChart.Data(String.valueOf(group), k1_avg));
            k2AvgSeries.getData().add(new XYChart.Data(String.valueOf(group), k2_avg));
            k1k2AvgSeries.getData().add(new XYChart.Data(String.valueOf(group), k1k2Avg));
        }
        k1AvgSeries.setName("K1 Average");
        k2AvgSeries.setName("K2 Average");
        k1k2AvgSeries.setName("K1 and K2 Average");
        LineChart1.setCreateSymbols(false);
        LineChart1.getData().addAll(k1AvgSeries, k2AvgSeries, k1k2AvgSeries);
    }

    public void printArray(Person[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                System.out.println(array[i].getStudentid() + " " + array[i].getK1energy() + " " + array[i].getK2energy());
            }
        }
    }


}
