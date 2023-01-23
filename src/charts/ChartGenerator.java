package charts;
 
import javafx.application.Application;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.sound.sampled.Line;

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
    private List<CheckBox> countryBoxes = new ArrayList<>();

    public ChartGenerator() {
        this.showGDP = true;
        this.showBudget = false;
        this.showPersonnel = false;
        this.scraper = new DataScrape();
        chart = new VBox();
        for (int i = 0; i < 31; i++){
            CheckBox checkbox = new CheckBox(scraper.getData().get(i*8).getCountry());
            checkbox.setSelected(false);
            checkbox.setOnAction(event -> this.GDPseries());
            countryBoxes.add(checkbox);
        }
        
    }

    public void updateLineChart(){

        NumberAxis xAxis = new NumberAxis("GDP",2014,2021,1);
        NumberAxis yAxis = new NumberAxis();

        List<XYChart.Series<Number, Number>> GDPSeriesList = new ArrayList<>();
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        if (showGDP = true) {
            for (int i = 0; i < 31; i++){
                GDPSeriesList.add(this.GDPseries().get(i));
            }
        }

        lineChart.setTitle("GDP");

        for (int i = 0; i < 31; i++){
            final int index = i; 
            EventHandler<ActionEvent> checkBoxEventHandler = 
        e -> {
                if(countryBoxes.get(index).isSelected()){
                    lineChart.getData().add(GDPSeriesList.get(index));
                }
                else{
                    lineChart.getData().remove(GDPSeriesList.get(index));
                }
            };
            countryBoxes.get(i).setOnAction(checkBoxEventHandler);

        }
    

        this.chart.getChildren().addAll(countryBoxes);
        this.chart.getChildren().addAll(lineChart);

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
}










        


    



 
    
 
   
    

  
