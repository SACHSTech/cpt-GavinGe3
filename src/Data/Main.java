package Data;

import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        
        HBox basic = new HBox();

        HBox newPane = new HBox();
        newPane.prefWidth(800);

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

        //basic.getChildren().add(newChart.getChart());
        basic.getChildren().add(tabPane);
        basic.getChildren().add(newPane);

        
        
        Scene scene = new Scene(basic,1400, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws FileNotFoundException {

        launch(args);

        DataScrape newData = new DataScrape();

        Sorter sort = new Sorter();

      //  System.out.println(sort.selectionSort().toString());

        for (int i = 0; i< 248;i++){
            System.out.println(sort.selectionSort().get(i).getBudget());
        }

       // System.out.println(newData.getData().toString());

        
    }
}
