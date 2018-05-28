package user;

public class Address {
    private String street;
    private String city;
    private States state;
    private int zipCode;
    private String country = "United States";

    public Address(String street, String city, States state, int zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public States getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }
}
