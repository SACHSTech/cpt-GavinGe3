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
import javafx.scene.chart.BarChart;
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
import javax.xml.stream.events.DTD;


import Data.*;


public class barChartGenerator {

    private boolean showGDP;
    private boolean showBudget;
    private boolean showPersonnel;
    private DataScrape scraper;
    private VBox chart;
    private List<CheckBox> countryBoxes = new ArrayList<>();
    private int globalCount;

    

    public barChartGenerator(){
        scraper = new DataScrape();
        showBudget = true;
        chart = new VBox();
        for (int i = 0; i < 31; i++){
            CheckBox checkbox = new CheckBox(scraper.getData().get(i*8).getCountry());
            checkbox.setSelected(false);
            countryBoxes.add(checkbox);
        }
        globalCount = 0;
    }
    public void updateBarChart(){

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> barchart = new BarChart<>(xAxis, yAxis);

        List<XYChart.Series<String,Number>> newSeries = new ArrayList<>();
        
            for (int i = 0; i < 8; i++){
                XYChart.Series<String,Number> newData = new XYChart.Series<>();
                for (int x = i; x < 248; x=x+1*8){
                    newData.getData().add(new XYChart.Data<>(scraper.getData().get(x).getCountry(), scraper.getData().get(x).getBudget()));
                }
                newSeries.add(newData);
            }
        
       
        ChoiceBox<String> choicebox = new ChoiceBox<String>(FXCollections.observableArrayList("2014","2015","2016","2017","2018","2019","2020","2021"));
        
        choicebox.getSelectionModel().selectedItemProperty().addListener((observableTwo, oldValueTwo, newValueTwo) -> {
            
            for (int i = 1; i < 32; i++){
                final int index = i; 
                EventHandler<ActionEvent> checkBoxEventHandler = 
                e -> {
                    if(countryBoxes.get(index-1).isSelected()){
                        newSeries.get(Integer.parseInt(newValueTwo)-2014).getData().add(new XYChart.Data<>(scraper.getData().get(index * 8 - (2021-(Integer.parseInt(newValueTwo)))).getCountry(), scraper.getData().get(index * 8 - (2021-(Integer.parseInt(newValueTwo)))).getBudget()));
                        newSeries.get(Integer.parseInt(newValueTwo)-2014).getData().remove(new XYChart.Data<>(scraper.getData().get(index * 8 - (2021-(Integer.parseInt(newValueTwo)))).getCountry(), scraper.getData().get(index * 8 - (2021-(Integer.parseInt(newValueTwo)))).getBudget()));
                       
                        }
                
                    else{
                        XYChart.Data<String, Number> dataToRemove = new XYChart.Data<>(scraper.getData().get(index * 8 - (2021-(Integer.parseInt(newValueTwo)))).getCountry(), scraper.getData().get(index * 8 - (2021-(Integer.parseInt(newValueTwo)))).getBudget());
                        int indexOf = newSeries.get(Integer.parseInt(newValueTwo)-2014).getData().indexOf(dataToRemove);
                        newSeries.get(Integer.parseInt(newValueTwo)-2014).getData().remove(indexOf);
                       
                        }
                    };
                    countryBoxes.get(i-1).setOnAction(checkBoxEventHandler);
                }
             
       
          
            switch(newValueTwo){
                case "2014":
                barchart.getData().remove(newSeries.get(Integer.parseInt(oldValueTwo) - 2014));
                barchart.getData().add(newSeries.get(0));
                chart.getChildren().add(barchart);
                break;
              

                case "2015":
                barchart.getData().remove(newSeries.get(Integer.parseInt(oldValueTwo) - 2014));
                barchart.getData().add(newSeries.get(1));
                chart.getChildren().add(barchart);

                break;


                case "2016":
                barchart.getData().remove(newSeries.get(Integer.parseInt(oldValueTwo) - 2014));
                barchart.getData().add(newSeries.get(2));
                chart.getChildren().add(barchart);
              

                break;

                case "2017":
                barchart.getData().remove(newSeries.get(Integer.parseInt(oldValueTwo) - 2014));

                barchart.getData().add(newSeries.get(3));
                chart.getChildren().add(barchart);
              

                break;

                case "2018":
                barchart.getData().remove(newSeries.get(Integer.parseInt(oldValueTwo) - 2014));

                barchart.getData().add(newSeries.get(4));
                chart.getChildren().add(barchart);
              
                break;

                case "2019":
                barchart.getData().remove(newSeries.get(Integer.parseInt(oldValueTwo) - 2014));

                barchart.getData().add(newSeries.get(5));
                chart.getChildren().add(barchart);
                break;

                case "2020":
                barchart.getData().remove(newSeries.get(Integer.parseInt(oldValueTwo) - 2014));

                barchart.getData().add(newSeries.get(6));
                chart.getChildren().add(barchart);
              
                break;

                case "2021":
                barchart.getData().remove(newSeries.get(Integer.parseInt(oldValueTwo) - 2014));
                barchart.getData().add(newSeries.get(7));
                chart.getChildren().add(barchart);
              
                break;
            }
            
        });
        
        
        chart.getChildren().add(choicebox);
        chart.getChildren().addAll(countryBoxes);
              

        
    }
    public VBox getChart(){
        return this.chart;
    }
    
}
