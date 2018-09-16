package learning.rada.raul.testrecyclerview;

public class Country {

    private String countryName;
    private String countryCapital;

    /**
     * {@link Country} object constructor
     *
     * @param countryName    name of the Country
     * @param countryCapital capital of the Country
     */
    public Country(String countryName, String countryCapital) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

    /**
     * Getter method for the {@link Country} name
     *
     * @return the name of the {@link Country}
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Getter method for the {@link Country} capital
     *
     * @return the capital of the {@link Country}
     */
    public String getCountryCapital() {
        return countryCapital;
    }
}
