package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Table {

    private Sorter newSorter;
    private TableView<CountryDataPoint> newTable;
    private DataScrape newScraper;
    
    public Table(){
        newSorter = new Sorter();
        newTable = new TableView<CountryDataPoint>();
        newScraper = new DataScrape();
    }


    public void createTable(){

        TextField newSearch = new TextField();

        TableColumn<CountryDataPoint, String> nameColumn = new TableColumn<>("CountryName");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Country"));

        TableColumn<CountryDataPoint, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("Year"));

        TableColumn<CountryDataPoint, Double> budgetColumn = new TableColumn<>("Military Spending (Billions of USD)");
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));

        TableColumn<CountryDataPoint, Double> personnelColumn = new TableColumn<>("Personnel");
        personnelColumn.setCellValueFactory(new PropertyValueFactory<>("Personnel"));

        TableColumn<CountryDataPoint, Double> GDPColumn = new TableColumn<>("GDP (Billions of USD)");
        GDPColumn.setCellValueFactory(new PropertyValueFactory<>("GDP"));

        newTable.getColumns().addAll(nameColumn, yearColumn, budgetColumn, personnelColumn, GDPColumn);


        ObservableList<CountryDataPoint> newData = FXCollections.observableArrayList(newSorter.selectionSort());

        newTable.setItems(newData);
        

        newTable.prefWidth(800);

    }

    public TableView<CountryDataPoint> returnTable(){
        return this.newTable;
    }
}
