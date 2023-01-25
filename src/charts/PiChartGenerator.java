package charts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;
import Data.*;

public class PiChartGenerator {

    // Boolean values determining which to show
    private boolean boolShowGDP;
    private boolean boolShowPersonnel;
    private boolean boolShowBudget;
    
    // get this.scraper
    private DataScrape scraper;

    // new piechart
    private PieChart pieChart;
    
    // Create H and V box for pi chart
    private VBox PieChartVBox;
    private HBox choiceBoxHBox;

    // create dataseries for pichart
    private List<ObservableList<PieChart.Data>> pieChartData;
    
    // Int determining if data includes the USA or Not
    private int intWithUs;

    // Int determining the current year to show
    private int intCurrentDataset;
    
    public PiChartGenerator(){

        // Create the variables listed above
        boolShowGDP = false;
        boolShowPersonnel = false;
        boolShowBudget = false;
        scraper = new DataScrape();
        pieChart = new PieChart();
        PieChartVBox = new VBox();
        choiceBoxHBox = new HBox();
        pieChartData = FXCollections.observableArrayList();
        intWithUs = 0;
        intCurrentDataset = -1;
    }


    public void updatePiChart(){

        // Create three Dropdown Menus that determine the type of data, the year of the data, and if the data includes the US

        ComboBox<String> choiceUnitedStates = new ComboBox<String>(FXCollections.observableArrayList("With USA", "Without USA"));
        choiceUnitedStates.setPromptText("Please Select An Option");
        ComboBox<String> dataChoice = new ComboBox<String>(FXCollections.observableArrayList("GDP", "Military Budget", "Personnel"));
        dataChoice.setPromptText("Please Select Data Category");
        ComboBox<String> choicebox = new ComboBox<String>(FXCollections.observableArrayList("2014","2015","2016","2017","2018","2019","2020","2021"));
        choicebox.setPromptText("Please Select A Year");

        // Detects if the Dropdown Menu selecting if the data includes the US is being used
        choiceUnitedStates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{

            // Adds the US and resets the dataset
            if (newValue.equals("With USA")){
                this.intWithUs = 232;
                clearAndGetData(this.intCurrentDataset);
            }
            // Adds the US and resets the dataset
            else{
                this.intWithUs = 224;
                clearAndGetData(this.intCurrentDataset);

            }
        });

        // Detects if the Dropdown Menu selecting the datatype is toggled
        dataChoice.getSelectionModel().selectedItemProperty().addListener((observableTwo, oldValueTwo, newValueTwo) -> {

            // If selection isn't empty
            if (!dataChoice.getSelectionModel().isEmpty()){

                // Displays GDP data if GDP is selected
                if (newValueTwo.equals("GDP")){
                    this.boolShowPersonnel = false;
                    this.boolShowBudget = false;
                    this.boolShowGDP = true;
                    clearAndGetData(this.intCurrentDataset);
                }
                // Displays Military Budget if GDP is selected
                else if (newValueTwo.equals("Military Budget")){
                    this.boolShowGDP = false;
                    this.boolShowPersonnel = false;
                    this.boolShowBudget = true;
                    clearAndGetData(this.intCurrentDataset);
    
                }
                // Displays Personnel data if Personnel is being selected
                else if (newValueTwo.equals("Personnel")){
                    this.boolShowGDP = false;
                    this.boolShowBudget = false;
                    this.boolShowPersonnel = true;
                    clearAndGetData(this.intCurrentDataset);
                }
            }
        
        });
        
        /// Detects if the Dropdown Menu selecting the year is toggled
        choicebox.getSelectionModel().selectedItemProperty().addListener((observableThree, oldValueThree, newValueThree) -> {

            // Update the int determining the current dataset 
            this.intCurrentDataset = Integer.parseInt(newValueThree)-2014;

            // Updates the current dataset
            if (!choicebox.getSelectionModel().isEmpty()){
                clearAndGetData(this.intCurrentDataset);
            }

        });

        // Add Dropdownmenus to HBox, then add that to VBox
        this.choiceBoxHBox.getChildren().addAll(choiceUnitedStates, dataChoice, choicebox);
        this.PieChartVBox.getChildren().addAll(this.choiceBoxHBox);
    }

    // Return the chart
    public VBox returnChart(){
        return this.PieChartVBox;
    }

    
    public void resetData(int intWithUs){

        // Create 8 Observable list containing piechart data and add to pieChartData
        for (int i = 0; i < 8; i++){
            ObservableList<PieChart.Data> newData = FXCollections.observableArrayList();
            this.pieChartData.add(newData);
        }
        // if showBudget then set the data to budget data
        if (this.boolShowBudget){
            for (int i = 0; i < 8; i++){
                for (int x = i; x < this.intWithUs; x=x+1*8){
                    this.pieChartData.get(i).add(new PieChart.Data(this.scraper.getData().get(x).getCountry(), this.scraper.getData().get(x).getBudget()));
                }
            }
        }
        // if showGDP then set the data to GDP data
        if (this.boolShowGDP){
            for (int i = 0; i < 8; i++){
                for (int x = i; x < this.intWithUs; x=x+1*8){
                    this.pieChartData.get(i).add(new PieChart.Data(this.scraper.getData().get(x).getCountry(), this.scraper.getData().get(x).getGDP()));
                }
            }
        }
        // if personnel then set the data to Personnel data
        if (this.boolShowPersonnel){

            for (int i = 0; i < 8; i++){
                for (int x = i; x < this.intWithUs; x=x+1*8){
                    this.pieChartData.get(i).add(new PieChart.Data(this.scraper.getData().get(x).getCountry(), this.scraper.getData().get(x).getPersonnel()));
                }
            }
        }
    }



    public void clearAndGetData(int intCurrentDataset){

        // When current dataset has been set
        if (this.intCurrentDataset > -1){

            // Clear all data from piChart datalist, and pichart and reform the pichart with new selected dataset
            this.pieChartData.clear();
            
            resetData(this.intWithUs);
            this.PieChartVBox.getChildren().remove(this.pieChart);
            this.pieChart.getData().clear();
            this.pieChart = new PieChart(this.pieChartData.get(this.intCurrentDataset));

            // Set width and height of pichart
            this.pieChart.setPrefWidth(1000);
            this.pieChart.setPrefHeight(600);

            // Set titles for Pichart
            if (this.boolShowBudget){
                this.pieChart.setTitle("Division of Military Budget in NATO Countries");
            }
            if (this.boolShowGDP){
                this.pieChart.setTitle("Division of Total GDP in NATO Countries");
            }
            if (this.boolShowPersonnel){
                this.pieChart.setTitle("Division of Active Military Personnel in NATO Countries");
            }

            // Add new Pichart to VBox
            this.PieChartVBox.getChildren().addAll(pieChart);
            
        }
        
    }

}
