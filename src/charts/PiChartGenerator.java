package charts;

 
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.sound.sampled.Line;
import javax.swing.event.ChangeListener;
import Data.*;

public class PiChartGenerator {
    private boolean showGDP;
    private boolean showPersonnel;
    private boolean showBudget;

    private int year;
    private DataScrape scraper;

    private PieChart pieChart;
    private VBox pieChartBox;



    public PiChartGenerator(){
        showBudget = true;
        showPersonnel = false;
        showBudget = false;
        scraper = new DataScrape();
        pieChart = new PieChart();
        pieChartBox = new VBox();
        year = 2014;
       
    }



    public void updatePiChart(){
        List<ObservableList<PieChart.Data>> pieChartData = FXCollections.observableArrayList();

        for (int i = 0; i < 8; i++){
            ObservableList<PieChart.Data> newData = FXCollections.observableArrayList();
            pieChartData.add(newData);
        }

        ChoiceBox<String> dataChoice = new ChoiceBox<String>(FXCollections.observableArrayList("GDP", "Military Budget", "Personnel"));

        dataChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("GDP")){
                showPersonnel = false;
                showBudget = false;
                showGDP = true;
            }
            else if (newValue.equals("Military Budget")){
                showGDP = false;
                showPersonnel = false;
                showBudget = true;
            }
            else if (newValue.equals("Personnel")){
                showGDP = false;
                showBudget = false;
                showPersonnel = true;
            }
        });

        
        ChoiceBox<String> choicebox = new ChoiceBox<String>(FXCollections.observableArrayList("2014","2015","2016","2017","2018","2019","2020","2021"));
        
        choicebox.getSelectionModel().selectedItemProperty().addListener((observableTwo, oldValueTwo, newValueTwo) -> {

            for (int i = 0; i < 8; i++){
                ObservableList<PieChart.Data> newData = FXCollections.observableArrayList();
                pieChartData.add(newData);
            }

            if (showBudget){
                for (int i = 0; i < 8; i++){
                    for (int x = i; x < 232; x=x+1*8){
                        pieChartData.get(i).add(new PieChart.Data(scraper.getData().get(x).getCountry(), scraper.getData().get(x).getBudget()));
                    }
                }
            }
            if (showGDP){
                for (int i = 0; i < 8; i++){
                    for (int x = i; x < 232; x=x+1*8){
                        pieChartData.get(i).add(new PieChart.Data(scraper.getData().get(x).getCountry(), scraper.getData().get(x).getGDP()));
                    }
                }
            }
            if (showPersonnel){
                for (int i = 0; i < 8; i++){
                    for (int x = i; x < 232; x=x+1*8){
                        pieChartData.get(i).add(new PieChart.Data(scraper.getData().get(x).getCountry(), scraper.getData().get(x).getPersonnel()));
                    }
                }
            }
        
            switch(newValueTwo){
                case "2014":
                pieChartBox.getChildren().remove(pieChart);
                pieChart.getData().clear();
                pieChart = new PieChart(pieChartData.get(0));
                pieChartBox.getChildren().addAll(pieChart);
                pieChartData.clear();
                break;

                case "2015":
                pieChartBox.getChildren().remove(pieChart);
                pieChart.getData().clear();
                this.pieChart = new PieChart(pieChartData.get(1));
                pieChartBox.getChildren().addAll(pieChart);
                pieChartData.clear();

                break;


                case "2016":
                pieChartBox.getChildren().remove(pieChart);
                pieChart.getData().clear();
                this.pieChart = new PieChart(pieChartData.get(2));
                pieChartBox.getChildren().addAll(pieChart);
                pieChartData.clear();

                break;

                case "2017":

                pieChartBox.getChildren().remove(pieChart);
                pieChart.getData().clear();
                this.pieChart = new PieChart(pieChartData.get(3));
                pieChartBox.getChildren().addAll(pieChart);
                pieChartData.clear();
                break;

                case "2018":

                pieChartBox.getChildren().remove(pieChart);
                pieChart.getData().clear();
                this.pieChart = new PieChart(pieChartData.get(4));
                pieChartBox.getChildren().add(pieChart);
                pieChartData.clear();

                break;

                case "2019":

                pieChartBox.getChildren().remove(pieChart);
                pieChart.getData().clear();
                this.pieChart = new PieChart(pieChartData.get(5));
                pieChartBox.getChildren().addAll(pieChart);
                pieChartData.clear();


                break;

                case "2020":
                pieChartBox.getChildren().remove(pieChart);
                pieChart.getData().clear();
                this.pieChart = new PieChart(pieChartData.get(6));
                pieChartBox.getChildren().addAll(pieChart);
                pieChartData.clear();

                break;

                case "2021":
                pieChartBox.getChildren().remove(pieChart);
                pieChart.getData().clear();
                this.pieChart = new PieChart(pieChartData.get(7));
                pieChartBox.getChildren().addAll(pieChart);
                pieChartData.clear();
                break;
            }

        });

        pieChartBox.getChildren().addAll(choicebox, dataChoice);
        pieChart.setTitle("GDP Distribution per Country");

        
    }
    public VBox returnChart(){
        return this.pieChartBox;
    }



    
}
