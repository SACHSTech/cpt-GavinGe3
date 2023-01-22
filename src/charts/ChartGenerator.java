package charts;
 
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
 
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
    

    public ChartGenerator() {
        this.showGDP = true;
        this.showBudget = false;
        this.showPersonnel = false;
        this.scraper = new DataScrape();
        chart = new VBox();

    }

    public void lineChart(){

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Test");

        if (showGDP = true){
            lineChart.getData().add(this.getGDPData());
        }

        this.chart.getChildren().add(lineChart);

    }

    public VBox getChart(){
        return chart;
    }

    public XYChart.Series<Number,Number> getGDPData(){

        XYChart.Series<Number, Number> GDPdata = new XYChart.Series<>();
        for (int i = 0; i < 247; i++ ){
            GDPdata.getData().add(new XYChart.Data<>(scraper.getData().get(i).getYear(),scraper.getData().get(i).getGDP()));
        }
        return GDPdata;


    }








        


    }



 
    
 
   
    

  
