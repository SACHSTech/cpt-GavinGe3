package Data;
import java.util.ArrayList;
import java.util.List;


public class Sorter {

    private DataScrape scrape;

    public Sorter(){
        // Create new scraper
        scrape = new DataScrape();
    }

    public List<CountryDataPoint> selectionSort(){

        // Create new list
        List<CountryDataPoint> sortedList = new ArrayList<CountryDataPoint>();

        // Add unsorted data to list
        sortedList = scrape.getData();
        int currentMinIndex;

        
        for (int i = 0; i < sortedList.size(); i++){
            // Set Current Min Index to first value of unsorted list
            currentMinIndex = i;

            
            for(int j = i + 1; j < sortedList.size(); j++){
                // Set Current Min Index to smallest value of unsorted list
                if (sortedList.get(j).getMilitaryBudget() < sortedList.get(currentMinIndex).getMilitaryBudget()){
                    currentMinIndex = j;
                }
            }

            // Swap the indexes to sort
            if (i != currentMinIndex){
                CountryDataPoint temp = sortedList.get(currentMinIndex);
                sortedList.set(currentMinIndex, sortedList.get(i));
                sortedList.set(i, temp);
            }
        }
        // Return sorted list based on military Spending
        return sortedList;
    }

    
}
