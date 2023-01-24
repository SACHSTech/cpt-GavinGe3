package Data;

import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import charts.ChartGenerator;
import charts.PiChartGenerator;


public class Main extends Application{

    public void start(Stage primaryStage) {

        Table newTable = new Table();
        newTable.createTable();

        ChartGenerator newChart = new ChartGenerator();
        newChart.updateLineChart();
        newChart.updateBarChart();
        PiChartGenerator newPiChart = new PiChartGenerator();
        newPiChart.updatePiChart();
        
        VBox basic = new VBox();
        VBox newPane = new VBox();
        newPane.getChildren().add(newTable.searchBar());
        newPane.getChildren().add(newTable.returnTable());
        

        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();
        tab1.setText("LineChart");

        Tab tab2 = new Tab();
        tab2.setText("PiChart");

        Tab tab3 = new Tab();
        tab3.setText("BarChart");

        tab1.setContent(newChart.getChart());
        tab2.setContent(newPiChart.returnChart());
        tab3.setContent(newChart.getBarChart());
        tabPane.getTabs().addAll(tab1,tab2,tab3);


        Label label = new Label("NATO Military and Economy Statistics");
        label.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;-fx-text-alignment: center;");

        label.setAlignment(Pos.TOP_CENTER);
        basic.getChildren().add(label);
        basic.getChildren().add(tabPane);
        basic.getChildren().add(newPane);


      
        Scene scene = new Scene(basic, 1000, 900);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws FileNotFoundException {

        launch(args);

 
    }
}
