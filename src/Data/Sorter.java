package Data;
import java.util.ArrayList;
import java.util.List;


public class Sorter {

    private DataScrape scrape;

    public Sorter(){
        scrape = new DataScrape();
    }


    public List<CountryDataPoint> selectionSort(){

        List<CountryDataPoint> Sorter = new ArrayList<CountryDataPoint>();
        Sorter = scrape.getData();
        int currentMinIndex;

        for (int i = 0; i < Sorter.size(); i++){


            currentMinIndex = i;

            for(int j = i + 1; j < Sorter.size(); j++){
                if (Sorter.get(j).getBudget() < Sorter.get(currentMinIndex).getBudget()){
                    currentMinIndex = j;
                }
            }

            if (i != currentMinIndex){
                CountryDataPoint temp = Sorter.get(currentMinIndex);
                Sorter.set(currentMinIndex, Sorter.get(i));
                Sorter.set(i, temp);
            }
        }
        return Sorter;
    }

    
}
