package Data;

public class CountryDataPoint {

    // Create Variables for the fields of each datapoint 
    private String Country;
    private int year;
    private double budget;
    private double personnel;
    private double GDP;
    public int addDataCounter;

    public CountryDataPoint() {
        // Create a counter for adding the datapoints
        addDataCounter = 0;
    }
    public void addValue(String value){

        // Switchcase that parses the value from the string
        switch(this.addDataCounter){
            case 0:
                this.Country = value;
                break;
            case 1:
                this.year = Integer.parseInt(value);
                break;
            case 2:
                this.budget = Double.parseDouble(value);
                break;
            case 3:
                this.personnel = Double.parseDouble(value);
                break;
            case 4:
                this.GDP = Double.parseDouble(value);
                break;
        }
        this.addDataCounter++;
    }

   
    public String getCountry() {
        return this.Country;
    }
    public Double getGDP(){
        return this.GDP;
    }
    public int getYear(){
        return this.year;
    }
    public double getPersonnel(){
        return this.personnel;
    }
    public double getMilitaryBudget(){
        return this.budget;
    }
    public void setBudget(double newValue){
        this.budget = newValue;
    }
    public void setPersonnel(double newValue){
         this.personnel = newValue;
    }
    public void setGDP(double newValue){
         this.GDP = newValue;
    }
}
