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
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.sound.sampled.Line;
import javax.swing.event.ChangeListener;
import Data.*;
 
/**
 * A chart that displays rectangular bars with heights indicating data values
 * for categories. Used for displaying information when at least one axis has
 * discontinuous or discrete data.
 */
public class ChartGenerator  {
 
    private boolean showGDP;
    private boolean showBudget;
    private boolean showPersonnel;
    private DataScrape scraper;
    private VBox chart;
    private ScrollPane boxPane;
    private VBox checkBoxes;
    private List<CheckBox> countryBoxes = new ArrayList<>();


    public ChartGenerator() {
        this.showGDP = true;
        this.showBudget = false;
        this.showPersonnel = false;
        this.scraper = new DataScrape();
        this.chart = new VBox();
        for (int i = 0; i < 31; i++){
            CheckBox checkbox = new CheckBox(scraper.getData().get(i*8).getCountry());
            checkbox.setSelected(false);
            countryBoxes.add(checkbox);
        }
        this.boxPane = new ScrollPane();
        this.checkBoxes = new VBox();


    }

    
    public void updateLineChart(){

        ChoiceBox<String> dataType = new ChoiceBox<String>(FXCollections.observableArrayList("Personnel", "GDP", "Military Spending"));
        
        List<XYChart.Series<Number, Number>> CurrentSeriesList = new ArrayList<>();
        NumberAxis xAxis = new NumberAxis("Year",2014,2021,1);
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        dataType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue ) -> {
            if(newValue.equals("Personnel")){
                lineChart.setTitle("Personnel");
                lineChart.getData().clear();
                CurrentSeriesList.clear();

                for (int i = 0; i < 31; i++){
                    countryBoxes.get(i).setSelected(false);

                    CurrentSeriesList.add(this.personnelSeries().get(i));
                }
            }
            else if (newValue.equals("GDP")){
                lineChart.setTitle("GDP");
                lineChart.getData().clear();
                CurrentSeriesList.clear();

                for (int i = 0; i < 31; i++){
                    countryBoxes.get(i).setSelected(false);
                    CurrentSeriesList.add(this.GDPseries().get(i));
                }
            }
            else if (newValue.equals("Military Spending")){
                
                lineChart.setTitle("Military Spending");

                lineChart.getData().clear();
                CurrentSeriesList.clear();

                for (int i = 0; i < 31; i++){
                    countryBoxes.get(i).setSelected(false);
                    CurrentSeriesList.add(this.budgetSeries().get(i));
                }
             
            }
        });

        for (int i = 0; i < 31; i++){
            final int index = i; 
            EventHandler<ActionEvent> checkBoxEventHandler = 
            e -> {
                if(countryBoxes.get(index).isSelected()){
                    lineChart.getData().add(CurrentSeriesList.get(index));
                }
                else{
                    lineChart.getData().remove(CurrentSeriesList.get(index));
                }
            };
            countryBoxes.get(i).setOnAction(checkBoxEventHandler);
        }

        
        this.chart.getChildren().addAll(lineChart, dataType);
        this.checkBoxes.getChildren().addAll(countryBoxes);
        this.boxPane.setContent(checkBoxes);
        this.chart.getChildren().addAll(boxPane);


    }
    public VBox getChart(){
        return chart;
    }

    public List<XYChart.Series<Number, Number>> GDPseries() {
        List<XYChart.Series<Number, Number>> GDPSeriesList = new ArrayList<>();
        for (int i = 0; i < 31; i++){
            XYChart.Series<Number, Number> GDPdata = new XYChart.Series<>();
            GDPdata.setName(scraper.getData().get(i*8).getCountry());
            
            for (int x = (i * 8); x < ((i+1) * 8); x++){
                GDPdata.getData().add(new XYChart.Data<>(scraper.getData().get(x).getYear(), scraper.getData().get(x).getGDP()));
                }
            
            
            GDPSeriesList.add(GDPdata);    
        }
        return GDPSeriesList;
    }

    public List<XYChart.Series<Number, Number>> personnelSeries() {
        List<XYChart.Series<Number, Number>> personnelSeriesList = new ArrayList<>();
        for (int i = 0; i < 31; i++){
            XYChart.Series<Number, Number> personnelData = new XYChart.Series<>();
            personnelData.setName(scraper.getData().get(i*8).getCountry());
            
            for (int x = (i * 8); x < ((i+1) * 8); x++){
                personnelData.getData().add(new XYChart.Data<>(scraper.getData().get(x).getYear(), scraper.getData().get(x).getPersonnel()));
                }
            
            
            personnelSeriesList.add(personnelData);    
    }
    return personnelSeriesList;
    }

    public List<XYChart.Series<Number, Number>> budgetSeries() {
        List<XYChart.Series<Number, Number>> budgetSeriesList = new ArrayList<>();
        for (int i = 0; i < 31; i++){
            XYChart.Series<Number, Number> budgetData = new XYChart.Series<>();
            budgetData.setName(scraper.getData().get(i*8).getCountry());
            
            for (int x = (i * 8); x < ((i+1) * 8); x++){
                budgetData.getData().add(new XYChart.Data<>(scraper.getData().get(x).getYear(), scraper.getData().get(x).getBudget()));
                }
            
            
            budgetSeriesList.add(budgetData);    
        }
    return budgetSeriesList;
    }


}










        


    



 
    
 
   
    

  
