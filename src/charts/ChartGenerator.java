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
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
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
    private HBox chart;
    private HBox barChart;
    private ScrollPane boxPane;
    private VBox checkBoxes;
    private VBox barCheckBoxes;
    private ScrollPane barScrollPane;

    private List<CheckBox> countryBoxes = new ArrayList<>();
    private List<CheckBox> barCountryBoxes = new ArrayList<>();



    public ChartGenerator() {
        this.showGDP = true;
        this.showBudget = false;
        this.showPersonnel = false;
        this.scraper = new DataScrape();
        this.chart = new HBox();
        for (int i = 0; i < 31; i++){
            CheckBox checkbox = new CheckBox(scraper.getData().get(i*8).getCountry());
            checkbox.setSelected(false);
            countryBoxes.add(checkbox);
        }
        for (int i = 0; i < 31; i ++){
            CheckBox checkbox = new CheckBox(scraper.getData().get(i*8).getCountry());
            checkbox.setSelected(false);
            barCountryBoxes.add(checkbox);
        }
        this.boxPane = new ScrollPane();
        boxPane.setPrefWidth(200);
        this.checkBoxes = new VBox();
        this.barChart = new HBox();
        this.barScrollPane = new ScrollPane();
        barScrollPane.setPrefWidth(200);
        this.barCheckBoxes = new VBox();
    }
    

    public void updateBarChart(){

        List<XYChart.Series<String,Number>> newSeries = new ArrayList<>();
        List<XYChart.Series<Number,Number>> holder = new ArrayList<>();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Years 2014 to 2021");
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barchart = new BarChart<>(xAxis, yAxis);
        

        barchart.setPrefHeight(600);
        barchart.setPrefWidth(850);

        ComboBox<String> comboBoxBar = new ComboBox<String>(FXCollections.observableArrayList("Military Personnel", "GDP", "Military Spending"));
        comboBoxBar.setPromptText("Select Data Category: ");


        addDataSeries(comboBoxBar, barchart, holder, newSeries, barCountryBoxes, 2, yAxis);

        this.barChart.getChildren().addAll(barchart);
        this.barCheckBoxes.getChildren().add(comboBoxBar);
        this.barCheckBoxes.getChildren().addAll(barCountryBoxes);
        this.barScrollPane.setContent(barCheckBoxes);
        this.barChart.getChildren().addAll(barScrollPane);

    }
    
    public void updateLineChart(){

        List<XYChart.Series<Number, Number>> CurrentSeriesList = new ArrayList<>();
        List<XYChart.Series<String, Number>> holder = new ArrayList<>();

        NumberAxis xAxis = new NumberAxis("Year",2014,2021,1);
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setPrefHeight(600);
        lineChart.setPrefWidth(850);

        ComboBox<String> comboBoxLine = new ComboBox<String>(FXCollections.observableArrayList("Military Personnel", "GDP", "Military Spending"));
        comboBoxLine.setPromptText("Select Data Category: ");

        addDataSeries(comboBoxLine, lineChart, CurrentSeriesList, holder, countryBoxes, 1, yAxis);

        
        this.chart.getChildren().addAll(lineChart, comboBoxLine);
        this.checkBoxes.getChildren().addAll(comboBoxLine);
        this.checkBoxes.getChildren().addAll(countryBoxes);
        this.boxPane.setContent(checkBoxes);
        this.chart.getChildren().addAll(boxPane);


    }


    public List<XYChart.Series<String, Number>> stringGDPseries() {
        List<XYChart.Series<String, Number>> stringGDPSeriesList = new ArrayList<>();
        for (int i = 0; i < 31; i++){
            XYChart.Series<String, Number> GDPdata = new XYChart.Series<>();
            GDPdata.setName(scraper.getData().get(i*8).getCountry());
            
            for (int x = (i * 8); x < ((i+1) * 8); x++){
                GDPdata.getData().add(new XYChart.Data<>(Integer.toString(scraper.getData().get(x).getYear()), scraper.getData().get(x).getGDP()));
                }
            
            
            stringGDPSeriesList.add(GDPdata);    
        }
        return stringGDPSeriesList;
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


    public List<XYChart.Series<String, Number>> stringPersonnelSeries() {
        List<XYChart.Series<String, Number>> stringPersonnelSeriesList = new ArrayList<>();
        for (int i = 0; i < 31; i++){
            XYChart.Series<String, Number> stringPersonnelData = new XYChart.Series<>();

            stringPersonnelData.setName(scraper.getData().get(i*8).getCountry());
            for (int x = (i * 8); x < ((i+1) * 8); x++){
                

                    stringPersonnelData.getData().add(new XYChart.Data<>(Integer.toString(scraper.getData().get(x).getYear()), scraper.getData().get(x).getPersonnel()));
                }
            
            
            stringPersonnelSeriesList.add(stringPersonnelData);    
    }
    return stringPersonnelSeriesList;
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


    public List<XYChart.Series<String,Number>> stringBudgetSeries(){
        List<XYChart.Series<String, Number>> stringBudgetSeriesList = new ArrayList<>();
        for (int i = 0; i < 31; i++){
            XYChart.Series<String, Number> budgetData = new XYChart.Series<>();
            budgetData.setName(scraper.getData().get(i*8).getCountry());
                for (int x = (i * 8); x < ((i+1) * 8); x++){
                  
                    budgetData.getData().add(new XYChart.Data<>(Integer.toString(scraper.getData().get(x).getYear()), scraper.getData().get(x).getBudget()));
                }
                stringBudgetSeriesList.add(budgetData);   

                }
    
            return stringBudgetSeriesList;
       

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


    public void addDataSeries(ComboBox selector, XYChart chart, 
    List<XYChart.Series<Number, Number>> a, List<XYChart.Series<String, Number>> b, List<CheckBox> checkBoxSet, int j, NumberAxis yAxis){
        
        selector.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("Military Personnel")){
                yAxis.setLabel("Personnel in Thousands");
                
                chart.setTitle("Number of Military Personnel in NATO Countries");
                chart.getData().clear();
                a.clear();
                b.clear();

                for (int i = 0; i < 31; i++){
                    checkBoxSet.get(i).setSelected(false);
                    if (j == 1){
                        a.add(this.personnelSeries().get(i));
                    }
                    else{
                        b.add(this.stringPersonnelSeries().get(i));
                    }
                    
                }
            }

            else if (newValue.equals("GDP")){
                yAxis.setLabel("GDP in Billions (USD)");

                chart.setTitle("Gross Domestic Product in NATO Countries (USD)");
                chart.getData().clear();
                a.clear();
                b.clear();


                for (int i = 0; i < 31; i++){
                    checkBoxSet.get(i).setSelected(false);
                    if (j == 1){
                        a.add(this.GDPseries().get(i));
                    }
                    else{
                        b.add(this.stringGDPseries().get(i));
                    }
            
                    }

                }
            
            else if (newValue.equals("Military Spending")){

                yAxis.setLabel("Military Budget in Millions (USD)");
                chart.setTitle("Military Spending of NATO Countries (USD)");

                chart.getData().clear();
                a.clear();
                b.clear();


                for (int i = 0; i < 31; i++){
                    checkBoxSet.get(i).setSelected(false);
                    if (j == 1){
                        a.add(this.budgetSeries().get(i));
                    }
                    else{
                        b.add(this.stringBudgetSeries().get(i));
                    }
             
            }
        }
        });

        for (int i = 0; i < 31; i++){
            final int index = i; 
            EventHandler<ActionEvent> checkBoxEventHandler = 
            e -> {
                if(checkBoxSet.get(index).isSelected()){
                    if (j == 1){
                        chart.getData().add(a.get(index));
                    }
                    else{
                        chart.getData().add(b.get(index));
                    }
                    
                }
                else{
                    if (j == 1){
                        chart.getData().remove(a.get(index));
                    }
                    else{
                        chart.getData().remove(b.get(index));
                    }
                }
            };
            checkBoxSet.get(i).setOnAction(checkBoxEventHandler);
        }
    }
    public HBox getChart(){
        return chart;
    }
    
    public HBox getBarChart(){
        return barChart;
    }
}










        


    



 
    
 
   
    

  
