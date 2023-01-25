package Data;

import java.util.ArrayList;
import java.util.List;

public class Sorter {
    // Class variable datascraper
    private DataScrape scrape;

    /**
     * Constructor for Sorter Class
     */
    public Sorter() {
        // Create new scraper
        scrape = new DataScrape();
    }

    /**
     * A method that sorts the list of datapoints obtained using the scraper by
     * Military Budget from low to high
     * 
     * @return list of CountryDataPoints from low to high based on military budget
     */
    public List<CountryDataPoint> sortOnBudget() {

        // Create new list
        List<CountryDataPoint> sortedList = new ArrayList<CountryDataPoint>();

        // Add unsorted data to list
        sortedList = this.scrape.getData();
        int currentMinIndex;

        for (int i = 0; i < sortedList.size(); i++) {
            // Set Current Min Index to first value of unsorted list
            currentMinIndex = i;

            for (int j = i + 1; j < sortedList.size(); j++) {
                // Set Current Min Index to smallest value of unsorted list
                if (sortedList.get(j).getBudget() < sortedList.get(currentMinIndex).getBudget()) {
                    currentMinIndex = j;
                }
            }

            // Swap the indexes to sort
            if (i != currentMinIndex) {
                CountryDataPoint temp = sortedList.get(currentMinIndex);
                sortedList.set(currentMinIndex, sortedList.get(i));
                sortedList.set(i, temp);
            }
        }
        // Return sorted list based on military Spending
        return sortedList;
    }

}
