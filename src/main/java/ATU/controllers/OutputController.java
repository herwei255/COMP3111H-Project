package ATU.controllers;

import ATU.scenes.LookUpScene;
import ATU.utils.ATUEngine;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;



import ATU.models.Person;

import java.io.*;

public class OutputController {

    @FXML
    private LineChart<String, Number> LineChart;

    @FXML
    private LineChart<String, Number> LineChart1;

    @FXML
    private Label label;

    @FXML
    private Button lookUp;

    @FXML
    private Label statistics;

    @FXML
    private Label text;

    @FXML
    private CategoryAxis x;

    @FXML
    private CategoryAxis x1;

    @FXML
    private NumberAxis y;

    @FXML
    private NumberAxis y1;

    @FXML
    void lookUpBtnOnPressed(ActionEvent event) {
        System.out.println("Look up button pressed");
        Stage stage = new Stage();
        stage.setTitle("Student Look Up");

        LookUpScene scene = null;
        try {
            scene = new LookUpScene();
            scene.getController().initLookUp(groupedArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();

    }
    // make a person array
    private Person[] personArr;
    private Person[][] groupedArr;

    // inititalize the person list
    public void initOutput(Person[] personArr) {
        // deep copy personArr into
        this.personArr = new Person[personArr.length];
        for (int i = 0; i < personArr.length; i++) {
            this.personArr[i] = new Person(personArr[i].getStudentid(), personArr[i].getStudentname(),
                    personArr[i].getEmail(), personArr[i].getK1energy(), personArr[i].getK2energy(),
                    personArr[i].getK3trick1(), personArr[i].getK3trick2(), personArr[i].getMypreference(),
                    personArr[i].getConcerns());
        }

        // sort the person array by desending k1
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

        // create ATUengine
        ATUEngine atuEngine = new ATUEngine(personArr);
        groupedArr = atuEngine.createGroups();
        plotTeamAverage(groupedArr);
        calculateStatistics(groupedArr);
    }

    void plotStudentKeyEnergy(Person[] persons) {
        // create serires
        XYChart.Series k1Series = new XYChart.Series();
        XYChart.Series k2Series = new XYChart.Series();
        k1Series.setName("K1 Energy");
        k2Series.setName("K2 Energy");
        LineChart.setCreateSymbols(false);

        // add the data to the chart
        for (int i = 0; i < persons.length; i++) {
            Person p = persons[i];
            k1Series.getData().add(new XYChart.Data(String.valueOf(i + 1), Integer.parseInt(p.getK1energy())));
            k2Series.getData().add(new XYChart.Data(String.valueOf(i + 1), Integer.parseInt(p.getK2energy())));
        }
        LineChart.getData().addAll(k1Series, k2Series);
    }

    void calculateStatistics(Person[][] groups) {
        int numGroups = groups.length;

        // calculate the average of each group
        float[] k1k2AverageArr = new float[numGroups];
        for (int i = 0; i < groups.length; i++) {
            int group = i + 1;
            int k1_sum = 0;
            int k2_sum = 0;
            int personCount = 0;
            for (int j = 0; j < groups[i].length; j++) {
                // calculate the average energy level of each group
                if (groups[i][j] != null) {
                    k1_sum += Integer.parseInt(groups[i][j].getK1energy());
                    k2_sum += Integer.parseInt(groups[i][j].getK2energy());
                    personCount++;
                }
            }

            float k1_avg = (float) k1_sum / personCount;
            float k2_avg = (float) k2_sum / personCount;
            float k1k2Avg = (k1_avg + k2_avg) / 2;
            k1k2AverageArr[i] = k1k2Avg;
        }
        double k1k2Average = 0;
        for (int i = 0; i < k1k2AverageArr.length; i++) {
            k1k2Average += k1k2AverageArr[i];
        }
        k1k2Average = k1k2Average / numGroups;

        //get max and min by for loop
        float max = k1k2AverageArr[0];
        float min = k1k2AverageArr[0];
        for (int i = 0; i < k1k2AverageArr.length; i++) {
            if (k1k2AverageArr[i] > max) {
                max = k1k2AverageArr[i];
            }
            if (k1k2AverageArr[i] < min) {
                min = k1k2AverageArr[i];
            }
        }

        // calculate the standard deviation
        double sum = 0;
        for (int i = 0; i < k1k2AverageArr.length; i++) {
            sum += Math.pow(k1k2AverageArr[i] - k1k2Average, 2);
        }
        double standardDeviation = Math.sqrt(sum / numGroups);
        //calculate the variance
        double variance = Math.pow(standardDeviation, 2);
        String formattedString = String.format("""
                    Statistics for %d groups \n
                    Mean    : %.2f \n
                    Max      : %.2f \n
                    Min      : %.2f \n
                    S.D       : %.2f \n
                    Variance : %.2f \n
                    """, numGroups, k1k2Average, max, min, standardDeviation, variance);
        statistics.setText(formattedString);
    }

    void plotTeamAverage(Person[][] groups) {
        // create a k1 and k2, average, array list

        XYChart.Series<String, Number> k1AvgSeries = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> k2AvgSeries = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> k1k2AvgSeries = new XYChart.Series<String, Number>();

        k1AvgSeries.setName("K1 Average");
        k2AvgSeries.setName("K2 Average");
        k1k2AvgSeries.setName("K1 and K2 Average");

        // print out groups
        for (int i = 0; i < groups.length; i++) {
            int group = i + 1;
            int k1_sum = 0;
            int k2_sum = 0;
            int personCount = 0;
            for (int j = 0; j < groups[i].length; j++) {
                // calculate the average energy level of each group
                if (groups[i][j] != null) {
                    k1_sum += Integer.parseInt(groups[i][j].getK1energy());
                    k2_sum += Integer.parseInt(groups[i][j].getK2energy());
                    personCount++;
                }
            }

            float k1_avg = (float) k1_sum / personCount;
            float k2_avg = (float) k2_sum / personCount;
            float k1k2Avg = (k1_avg + k2_avg) / 2;
            XYChart.Data<String, Number> k1AvgData = new XYChart.Data<String, Number>(String.valueOf(group), k1_avg);
            XYChart.Data<String, Number> k2AvgData = new XYChart.Data<String, Number>(String.valueOf(group), k2_avg);
            XYChart.Data<String, Number> k1k2AvgData = new XYChart.Data<String, Number>(String.valueOf(group), k1k2Avg);
            k1AvgSeries.getData().add(k1AvgData);
            k2AvgSeries.getData().add(k2AvgData);
            k1k2AvgSeries.getData().add(k1k2AvgData);
        }

        // LineChart1.setCreateSymbols(false);
        LineChart1.getData().addAll(k1AvgSeries, k2AvgSeries, k1k2AvgSeries);

        k1AvgSeries.getNode().setOnMouseClicked(e -> System.out.println("K1 Average"));
        // create tooltip
        for (int i = 0; i < k1AvgSeries.getData().size(); i++) {
            XYChart.Data<String, Number> data = k1AvgSeries.getData().get(i);
            String formattedString = String.format("Team %d \n K1 Average: %.2f",i+1,data.getYValue());
            Tooltip tooltip = new Tooltip();
            tooltip.setText(formattedString);
            Tooltip.install(data.getNode(), tooltip);
        }
        for (int i = 0; i < k2AvgSeries.getData().size(); i++) {
            XYChart.Data<String, Number> data = k2AvgSeries.getData().get(i);
            String formattedString = String.format("Team %d \n K2 Average: %.2f",i+1,data.getYValue());
            Tooltip tooltip = new Tooltip();
            tooltip.setText(formattedString);
            Tooltip.install(data.getNode(), tooltip);
        }
        for (int i = 0; i < k1k2AvgSeries.getData().size(); i++) {
            XYChart.Data<String, Number> data = k1k2AvgSeries.getData().get(i);
            String formattedString = String.format(" Team %d \n K1 + K2 Average: %.2f \n K1 Average: %.2f \n K2 Average: %.2f", i+1 ,data.getYValue(),k1AvgSeries.getData().get(i).getYValue(), k2AvgSeries.getData().get(i).getYValue());
            data.getNode().setOnMouseClicked(e -> label.setText(formattedString));
        }
    }
}
