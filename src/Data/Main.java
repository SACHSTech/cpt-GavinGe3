package Data;

/**
 * Main class of program. Where objects of classes are initialized to generate the graphs to the screen.
 * 
 * @author G. Ge
 */
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import charts.BarAndLineChartGenerator;
import charts.PiChartGenerator;


public class Main extends Application{

    /**
     * A method that sets the stage for the JavaFX application containg a line, bar, and pichart
     */
    public void start(Stage primaryStage) {

        // Create new Table
        Table newTable = new Table();
        newTable.createTable();

        // Create newLine and Bar Chart
        BarAndLineChartGenerator newChart = new BarAndLineChartGenerator();
        newChart.updateLineChart();
        newChart.updateBarChart();

        // Create PiChart
        PiChartGenerator newPiChart = new PiChartGenerator();
        newPiChart.updatePiChart();
        
        // Create Main Vbox
        VBox mainVBox = new VBox();

        // Create new VBox for Table
        VBox newTableVBox = new VBox();
        newTableVBox.getChildren().add(newTable.searchBar());
        newTableVBox.getChildren().add(newTable.returnTable());
        

        // Create new tab pane, tabs and set names
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();
        tab1.setText("LineChart");

        Tab tab2 = new Tab();
        tab2.setText("PiChart");

        Tab tab3 = new Tab();
        tab3.setText("BarChart");

        // Set content for tabs and add to tab pane
        tab1.setContent(newChart.getChart());
        tab2.setContent(newPiChart.returnChart());
        tab3.setContent(newChart.getBarChart());
        tabPane.getTabs().addAll(tab1,tab2,tab3);

        // Create main Chart Label
        Label label = new Label("NATO Military and Economy Statistics");
        label.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;-fx-text-alignment: center;");

        // Add title, tabpane, and tableVbox to main VBox
        mainVBox.getChildren().add(label);
        mainVBox.getChildren().add(tabPane);
        mainVBox.getChildren().add(newTableVBox);

        // Set Scene to the mainVBox and set dimensions
        Scene scene = new Scene(mainVBox, 1075, 900);

        // Set primary stage to scene and show to screen
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * main Method that runs the JavaFX application
     * @param args
     * 
     */
    
    public static void main(String[] args)  {
        launch(args);

 
    }
}
