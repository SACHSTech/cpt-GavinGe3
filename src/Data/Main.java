package Data;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import charts.ChartGenerator;
public class Main extends Application{

    public void start(Stage primaryStage) {

        ChartGenerator newChart = new ChartGenerator();
        newChart.updateLineChart();

        Scene scene = new Scene(newChart.getChart(), 1000,1000);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void change(ActionEvent event){
        if (newChart.)
    }

    public static void main(String[] args) throws FileNotFoundException {

        launch(args);

        DataScrape newData = new DataScrape();

       for (int i = 0; i < 248; i++) {

            System.out.println((newData.getData().get(i)).getGDP());
       }
    }
}
