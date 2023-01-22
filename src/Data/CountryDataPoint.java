package Data;

public class CountryDataPoint {
    private String Country;
    private int year;
    private double budget;
    private double personnel;
    private double GDP;
    public int addData;

    public CountryDataPoint() {
        addData = 0;
    }

    public void add(String value){
        switch(addData){
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
        }
        this.addData++;
    }
    public String getCountry() {
        return this.Country;
    }

}
