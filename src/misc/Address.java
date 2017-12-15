package misc;

public class Address {

    private String country;
    private String governate;
    private String street;
    private int building;
    private int floor;
    private int apartment;

    public String getCountry() { return country; }

    public Address setCountry(String country) { this.country = country; return this; }

    public String getGovernate() { return governate; }

    public Address setGovernate(String governate) { this.governate = governate; return this; }

    public String getStreet() { return street; }

    public Address setStreet(String street) { this.street = street; return this; }

    public int getBuilding() { return building; }

    public Address setBuilding(int building) { this.building = building; return this; }

    public int getFloor() { return floor; }

    public Address setFloor(int floor) { this.floor = floor; return this; }

    public int getApartment() { return apartment; }

    public Address setApartment(int apartment) { this.apartment = apartment; return this; }

}
