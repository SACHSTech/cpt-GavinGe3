package Data;

import java.io.*;
import java.util.*;

public class DataScrape {

    // Class contains list of datapoints
    List<CountryDataPoint> datalist;

    public DataScrape() {

        try {

            // New Buffered reader that reads from csv
            BufferedReader reader = new BufferedReader(new FileReader("src/Data/RawData.csv"));

            // Change all occurences of string
            String strNewLine;
            List<CountryDataPoint> newDataList = new ArrayList<CountryDataPoint>();

            while ((strNewLine = reader.readLine()) != null) {

                // Split each line at comma, add to an array, and create a datapoint
                String[] newStr = strNewLine.split(",");
                CountryDataPoint datapoint = new CountryDataPoint();

                // Add the values in the array, to the datapoint
                for (int i = 0; i < 5; i++) {
                    datapoint.addValue(newStr[i]);
                }

                // Add datapoint to temporary list
                datalist.add(datapoint);

            }
            // Set the master datalist to the temporary list
            this.datalist = newDataList;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }

    public List<CountryDataPoint> getData() {
        return datalist;
    }

    public CountryDataPoint getCountry(int i) {
        return datalist.get(i);
    }

}
