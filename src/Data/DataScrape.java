package Data;

import java.io.*;
import java.util.*;



public class DataScrape {

    List<CountryDataPoint> datalist;
    


    public DataScrape(){

        try {
            
            BufferedReader reader = new BufferedReader(new FileReader("src/Data/RawData.csv"));
            String newline;
        
            List<CountryDataPoint> dataList = new ArrayList<CountryDataPoint>();

            while((newline = reader.readLine()) != null){
                String[] newStr = newline.split(",");
                CountryDataPoint datapoint = new CountryDataPoint();
                
                for (int i = 0; i < 5; i++) {
                    datapoint.addValue(newStr[i]);
                    //System.out.println(newStr[i]);
                }
                
                dataList.add(datapoint);
                

            }
            this.datalist = dataList;
            reader.close();
        } 
        catch (Exception e) {
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
