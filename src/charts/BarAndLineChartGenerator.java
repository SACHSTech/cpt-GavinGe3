package charts;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import Data.*;

public class BarAndLineChartGenerator {

    // Get Scraper
    private DataScrape scraper;

    // Create HBoxes for charts
    private HBox lineChartBox;
    private HBox barChartBox;

    // Create Scroll Panes for Vbox containing Checkboxes
    private ScrollPane barScrollPane;
    private ScrollPane lineScrollPane;

    // Create Vbox for checkboxes
    private VBox checkBoxes;
    private VBox barCheckBoxes;

    // Create Lists of Checkboxes
    private List<CheckBox> lineCountryBoxes = new ArrayList<>();
    private List<CheckBox> barCountryBoxes = new ArrayList<>();

    public BarAndLineChartGenerator() {

        // Create Data Scraper
        this.scraper = new DataScrape();

        // Create Hboxes for charts
        this.lineChartBox = new HBox();
        this.barChartBox = new HBox();

        // Populate Checkboxes for both charts
        for (int i = 0; i < 31; i++) {
            CheckBox checkbox = new CheckBox(scraper.getData().get(i * 8).getCountry());
            checkbox.setSelected(false);
            lineCountryBoxes.add(checkbox);
        }
        for (int i = 0; i < 31; i++) {
            CheckBox checkbox = new CheckBox(scraper.getData().get(i * 8).getCountry());
            checkbox.setSelected(false);
            barCountryBoxes.add(checkbox);
        }

        // Create and set width for Scrollpanes containing Vbox containing checkboxes
        this.lineScrollPane = new ScrollPane();
        this.barScrollPane = new ScrollPane();
        lineScrollPane.setPrefWidth(200);
        barScrollPane.setPrefWidth(200);

        // Create Vboxes that contain checkboxes
        this.checkBoxes = new VBox();
        this.barCheckBoxes = new VBox();

    }

    public void updateBarChart() {

        // Define New Data Series
        List<XYChart.Series<String, Number>> newSeries = new ArrayList<>();

        // Useless Data Series
        List<XYChart.Series<Number, Number>> holder = new ArrayList<>();

        // Create and set X and Y Axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Years 2014 to 2021");
        NumberAxis yAxis = new NumberAxis();

        // Create and set dimensions for new Barchart
        BarChart<String, Number> barchart = new BarChart<>(xAxis, yAxis);
        barchart.setPrefHeight(600);
        barchart.setPrefWidth(850);

        // Create new dropdown menu for choosing type of data
        ComboBox<String> barComboBox = new ComboBox<String>(
        FXCollections.observableArrayList("Military Personnel", "GDP", "Military Spending"));
        barComboBox.setPromptText("Select Data Category: ");

        // Run this method that adds data series based on Checkboxes and Data type
        // selected
        addDataSeries(barComboBox, barchart, holder, newSeries, barCountryBoxes, 2, yAxis);

        // Add all panes containing barchart, dropdown menu, and checkboxes to a Hbox
        this.barChartBox.getChildren().addAll(barchart);
        this.barCheckBoxes.getChildren().add(barComboBox);
        this.barCheckBoxes.getChildren().addAll(barCountryBoxes);
        this.barScrollPane.setContent(barCheckBoxes);
        this.barChartBox.getChildren().addAll(barScrollPane);

    }

    public void updateLineChart() {

        // Define New Data Series
        List<XYChart.Series<Number, Number>> CurrentSeriesList = new ArrayList<>();

        // Useless Data Series
        List<XYChart.Series<String, Number>> holder = new ArrayList<>();

        // Set Axises
        NumberAxis xAxis = new NumberAxis("Year", 2014, 2021, 1);
        NumberAxis yAxis = new NumberAxis();

        // Create and set dimensions for new linechart
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setPrefHeight(600);
        lineChart.setPrefWidth(850);

        // Create new dropdown menu for choosing type of data
        ComboBox<String> comboBoxLine = new ComboBox<String>(
                FXCollections.observableArrayList("Military Personnel", "GDP", "Military Spending"));
        comboBoxLine.setPromptText("Select Data Category: ");

        // Run this method that adds data series based on Checkboxes and Data type
        // selected
        addDataSeries(comboBoxLine, lineChart, CurrentSeriesList, holder, lineCountryBoxes, 1, yAxis);

        // Add all panes containing barchart, dropdown menu, and checkboxes to a Hbox
        this.lineChartBox.getChildren().addAll(lineChart, comboBoxLine);
        this.checkBoxes.getChildren().addAll(comboBoxLine);
        this.checkBoxes.getChildren().addAll(lineCountryBoxes);
        this.lineScrollPane.setContent(checkBoxes);
        this.lineChartBox.getChildren().addAll(lineScrollPane);

    }

    
    public List<XYChart.Series<String, Number>> stringCurrentSeries(Object selection) {
        
        // Creates arraylist of data series consisting for format String, Number
        List<XYChart.Series<String, Number>> stringSeriesList = new ArrayList<>();

        // For each of 31 countries add military personnel data for each year between 2014, 2021
        for (int i = 0; i < 31; i++) {
            // Create 31 data series, one for each country
            XYChart.Series<String, Number> stringData = new XYChart.Series<>();
            stringData.setName(scraper.getData().get(i * 8).getCountry());
            
            for (int x = (i * 8); x < ((i + 1) * 8); x++) {
                // Add Military personnel Data if that is selected to series
                if (selection.equals("Military Personnel")){
                    stringData.getData().add(new XYChart.Data<>(
                        Integer.toString(scraper.getData().get(x).getYear()), scraper.getData().get(x).getPersonnel()));
                }
                // Add GDP Data if that is selected to series
                if (selection.equals("GDP")){
                    stringData.getData().add(new XYChart.Data<>(
                        Integer.toString(scraper.getData().get(x).getYear()), scraper.getData().get(x).getGDP()));
                }
                // Add Military Spending Data if that is selected to series
                if (selection.equals("Military Spending")){
                    stringData.getData().add(new XYChart.Data<>(
                        Integer.toString(scraper.getData().get(x).getYear()), scraper.getData().get(x).getBudget()));
                }
            }
            // Add all data series to list
            stringSeriesList.add(stringData);
        }
        // return data series list
        return stringSeriesList;
    }

    public List<XYChart.Series<Number, Number>> currentSeries(Object newValue) {

        // Creates arraylist of data series consisting for format Number, Number
        List<XYChart.Series<Number, Number>> seriesList = new ArrayList<>();

        // For each of 31 countries add military personnel data for each year between 2014, 2021
        for (int i = 0; i < 31; i++) {

            // Create 31 data series, one for each country
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(scraper.getData().get(i * 8).getCountry());

            for (int x = (i * 8); x < ((i + 1) * 8); x++) {
                // Add Military personnel Data if that is selected to series
                if (newValue.equals("Military Personnel")){
                    series.getData().add(new XYChart.Data<>(
                        scraper.getData().get(x).getYear(), scraper.getData().get(x).getPersonnel()));
                }
                // Add GDP Data if that is selected to series
                if (newValue.equals("GDP")){
                    series.getData().add(new XYChart.Data<>(
                        scraper.getData().get(x).getYear(), scraper.getData().get(x).getGDP()));
                }
                // Add Military Spending Data if that is selected to series
                if (newValue.equals("Military Spending")){
                    series.getData().add(new XYChart.Data<>(
                        scraper.getData().get(x).getYear(), scraper.getData().get(x).getBudget()));
                }
            }
        // Add all data series to list
        seriesList.add(series);
        }
        // return data series list
        return seriesList;

    }


    public void addDataSeries(ComboBox selector, XYChart chart,
            List<XYChart.Series<Number, Number>> LineDataList, List<XYChart.Series<String, Number>> BarDataList,
            List<CheckBox> checkBoxSet, int intCounter, NumberAxis yAxis) {
        

        // Create Listener that detects change in dropdown Menu
        selector.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            // If user selects Military Personnel set Axis and get data
            if (newValue.equals("Military Personnel")) {
                yAxis.setLabel("Active Military Personnel in Thousands");

                chart.setTitle("Number of Military Personnel in NATO Countries");
                chart.getData().clear();
                LineDataList.clear();
                BarDataList.clear();

                
                for (int i = 0; i < 31; i++) {
                    checkBoxSet.get(i).setSelected(false);
                    if (intCounter == 1) {
                        LineDataList.add(this.currentSeries(newValue).get(i));
                    } else {
                        BarDataList.add(this.stringCurrentSeries(newValue).get(i));
                    }

                }
            }

            // If user selects GDP set Axis and get data
            else if (newValue.equals("GDP")) {
                yAxis.setLabel("GDP in Billions (USD)");

                chart.setTitle("Gross Domestic Product in NATO Countries (USD)");
                chart.getData().clear();
                LineDataList.clear();
                BarDataList.clear();

                for (int i = 0; i < 31; i++) {
                    checkBoxSet.get(i).setSelected(false);
                    if (intCounter == 1) {
                        LineDataList.add(this.currentSeries(newValue).get(i));
                    } else {
                        BarDataList.add(this.stringCurrentSeries(newValue).get(i));
                    }

                }

            }
            // If user selects Military Spending set Axis and get data
            else if (newValue.equals("Military Spending")) {

                yAxis.setLabel("Military Budget in Millions (USD)");
                chart.setTitle("Military Spending of NATO Countries (USD)");

                chart.getData().clear();
                LineDataList.clear();
                BarDataList.clear();

                for (int i = 0; i < 31; i++) {
                    checkBoxSet.get(i).setSelected(false);
                    if (intCounter == 1) {
                        LineDataList.add(this.currentSeries(newValue).get(i));
                    } else {
                        BarDataList.add(this.stringCurrentSeries(newValue).get(i));
                    }

                }
            }
        });

        // If Country Checkbox is ticked then add data to chart
        for (int i = 0; i < 31; i++) {
            final int index = i;
            EventHandler<ActionEvent> checkBoxEventHandler = e -> {
                if (checkBoxSet.get(index).isSelected()) {
                    if (intCounter == 1) {
                        chart.getData().add(LineDataList.get(index));
                    } else {
                        chart.getData().add(BarDataList.get(index));
                    }

                } else {
                    if (intCounter == 1) {
                        chart.getData().remove(LineDataList.get(index));
                    } else {
                        chart.getData().remove(BarDataList.get(index));
                    }
                }
            };
            checkBoxSet.get(i).setOnAction(checkBoxEventHandler);
        }
    }

    public HBox getChart() {
        return lineChartBox;
    }

    public HBox getBarChart() {
        return barChartBox;
    }
}
