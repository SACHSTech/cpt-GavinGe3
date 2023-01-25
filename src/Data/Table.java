package Data;

// import packages
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Table {

    // Create new Sorter
    private Sorter newSorter;

    // Create new Table and Search bar
    private TableView<CountryDataPoint> newTable;
    private TextField newSearch;

    /**
     * Constructor for table method
     * 
     */
    public Table() {
        newSorter = new Sorter();
        newTable = new TableView<CountryDataPoint>();
        newSearch = new TextField();
    }

    /**
     * A method that defines a Datatable with a searchbar that can be run in Main.
     */
    public void createTable() {

        // Set columns for table based on our datapoints
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

        // Add Columns to table
        this.newTable.getColumns().addAll(nameColumn, yearColumn, budgetColumn, personnelColumn, GDPColumn);

        // Create new observablelist of datapoints using the sorter
        ObservableList<CountryDataPoint> newData = FXCollections.observableArrayList(this.newSorter.sortOnBudget());

        // Set items on table to sorted datapoints
        this.newTable.setItems(newData);

        // Set width of table columns
        yearColumn.setPrefWidth(150);
        budgetColumn.setPrefWidth(300);
        personnelColumn.setPrefWidth(250);
        GDPColumn.setPrefWidth(250);

        // Searchbar prompt
        this.newSearch.setPromptText("Search by Country Name");

        // When a key is released
        this.newSearch.setOnKeyReleased(e -> {

            // Store searched term in a string and create new list of datapoints
            String searchTerm = this.newSearch.getText();
            ObservableList<CountryDataPoint> filteredData = FXCollections.observableArrayList();
            for (CountryDataPoint data : newData) {
                // datapoint matches searched term add to new list of datapoints
                if (data.getCountry().toLowerCase().contains(searchTerm.toLowerCase())) {
                    filteredData.add(data);
                }
            }
            // Set table to display filtered datapoints
            this.newTable.setItems(filteredData);
        });
    }

    public TableView<CountryDataPoint> returnTable() {
        return this.newTable;
    }

    public TextField searchBar() {
        return this.newSearch;
    }
}
