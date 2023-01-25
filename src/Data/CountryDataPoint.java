package Data;

public class CountryDataPoint {
    
    // Initialize Variables Contained within Datapoint
    private String Country;
    private int year;
    private double budget;
    private double personnel;
    private double GDP;
    public int addData;

    public CountryDataPoint() {
        addData = 0;
    }

    /**
     * Method that allows for the datapoint to be populated
     * 
     * @param value the value that is added to the datapoint as a string
     */
    public void addValue(String value) {

        // Switch case to add values to Datapoint
        switch (addData) {
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

        this.addData++;
    }

    /**
     * returns the datapoints country
     * 
     * @return String Country
     */
    public String getCountry() {
        // return country
        return this.Country;
    }

    /**
     * returns the datapoints GDP value
     * 
     * @return Double GDP
     */
    public Double getGDP() {

        // return country GDP
        return this.GDP;
    }

    /**
     * returns the year
     * 
     * @return int Year
     */
    public int getYear() {
        // return Year
        return this.year;
    }

    /**
     * Returns the personnel
     * 
     * @return double personnel
     */
    public double getPersonnel() {
        // return Military Personnel
        return this.personnel;
    }

    /**
     * Returns the datapoints military budget
     * 
     * @return double budget
     */
    public double getBudget() {
        // return Military Budget
        return this.budget;
    }

}