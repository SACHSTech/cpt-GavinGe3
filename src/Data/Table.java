package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class Table {

    private Sorter newSorter;
    private TableView<CountryDataPoint> newTable;
    private DataScrape newScraper;
    private TextField newSearch;
    
    public Table(){
        newSorter = new Sorter();
        newTable = new TableView<CountryDataPoint>();
        newScraper = new DataScrape();
        newSearch = new TextField();

    }


    public void createTable(){

      

        TableColumn<CountryDataPoint, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Country"));

        TableColumn<CountryDataPoint, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("Year"));

        TableColumn<CountryDataPoint, Double> budgetColumn = new TableColumn<>("Military Spending (Millions of USD)");
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));

        TableColumn<CountryDataPoint, Double> personnelColumn = new TableColumn<>("Personnel (Thousands)");
        personnelColumn.setCellValueFactory(new PropertyValueFactory<>("Personnel"));

        TableColumn<CountryDataPoint, Double> GDPColumn = new TableColumn<>("GDP (Billions of USD)");
        GDPColumn.setCellValueFactory(new PropertyValueFactory<>("GDP"));

        newTable.getColumns().addAll(nameColumn, yearColumn, budgetColumn, personnelColumn, GDPColumn);


        ObservableList<CountryDataPoint> newData = FXCollections.observableArrayList(newSorter.selectionSort());

        newTable.setItems(newData);

        yearColumn.setPrefWidth(150);
        personnelColumn.setPrefWidth(250);
        GDPColumn.setPrefWidth(250);

        newData.forEach(item ->{
            item.setBudget(item.getBudget() * 1000000);
            item.setPersonnel(item.getPersonnel() * 1000);
            item.setGDP(item.getGDP() * 1000000000);
        });


        newSearch.setPromptText("Search by Country Name");
        newSearch.setOnKeyReleased(e -> {
        String searchTerm = newSearch.getText();
        ObservableList<CountryDataPoint> filteredData = FXCollections.observableArrayList();
        for (CountryDataPoint data : newData) {
            if (data.getCountry().toLowerCase().contains(searchTerm.toLowerCase())) {
            filteredData.add(data);
            }
        }
        newTable.setItems(filteredData);
    });
    }

    public TableView<CountryDataPoint> returnTable(){
        return this.newTable;
    }
    public TextField searchBar(){
        return newSearch;
    }
}
