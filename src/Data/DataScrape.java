package Data;

/**
 * A class to scrape data from a .csv file adds it to a datapoint, store the datapoints in a list, and return the list
 * 
 * @author G. Ge
 */

import java.io.*;
import java.util.*;


public class DataScrape {

    // Class Variable list of datapoints
    List<CountryDataPoint> datalist;

    /**
     * When initialized this class scrapes data from the CSV File and stores it within an object List containing CountryDataPoints defined in a seperate class
     */
    
    public DataScrape(){

        try {
            // Create new Buffered reader to read file
            BufferedReader reader = new BufferedReader(new FileReader("src/Data/RawData.csv"));
            String newline;
            
            // Create Temporary list for datapoints
            List<CountryDataPoint> newDataList = new ArrayList<CountryDataPoint>();

            while((newline = reader.readLine()) != null){
                // Split lines by comma
                String[] newStr = newline.split(",");
                CountryDataPoint datapoint = new CountryDataPoint();
                
                // Add values to the datapoint
                for (int i = 0; i < newStr.length; i++) {
                    datapoint.addValue(newStr[i]);
                    //System.out.println(newStr[i]);
                }
                // Add datapoint to datapoint list
                newDataList.add(datapoint);
                

            }
            // set object datalist to temporary datalist
            this.datalist = newDataList;
            reader.close();
        } 

        catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    /**
     * returns the list of datapoints
     * @return datalist
     */
    public List<CountryDataPoint> getData() {
        // Return datalist
        return this.datalist;
    }

  
    
}