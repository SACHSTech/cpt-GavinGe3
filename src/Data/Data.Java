package Data;

import java.io.*;
import java.util.*;


public class Data {

    List<CountryDataPoint> datalist;


    public Data() throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader("src/Data/RawData.csv"));
        String newline = null;
    
        List<CountryDataPoint> dataList = new ArrayList<CountryDataPoint>();

        try {
            while((newline = reader.readLine()) != null){
                String[] newStr = newline.split(",");
            
                CountryDataPoint datapoint = new CountryDataPoint();
    
                for (int i = 0; i < 5; i++) {
                    datapoint.add(newStr[i]);
                }
    
                datalist.add(datapoint);
                
            }
    
            this.datalist = datalist;
            
        } catch (Exception e) {
            System.out.print("LMAO");
            // TODO: handle exception
        }
       
    }
    public CountryDataPoint getData(int i) {
        return datalist.get(i);
    }
}
