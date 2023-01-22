package Data;

import java.io.*;
import java.util.*;


public class Data {

    List<CountryDataPoint> datalist;


    public Data(){

       
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
            System.out.println(dataList.get(11).getCountry());
            this.datalist = dataList;
            reader.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
       
    }
    public CountryDataPoint getData(int i) {
        return datalist.get(i);
    }
}
