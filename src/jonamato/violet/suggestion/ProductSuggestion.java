package jonamato.violet.suggestion;

import org.dizitart.no2.objects.Id;

public class ProductSuggestion {

    @Id
    private String id;
    private String name;
    private String category;
    private String brand;

    public ProductSuggestion genID() { return setID(getBrand() + "\t" + getCategory() + "\t" + getName()); }

    public ProductSuggestion setID(String id) { this.id = id; return this; }

    public String getID() { return id; }

    public ProductSuggestion setName(String name) { this.name = name; return this; }

    public String getName() { return name; }

    public ProductSuggestion setCategory(String category) { this.category = category; return this; }

    public String getCategory() { return category; }

    public ProductSuggestion setBrand(String brand) { this.brand = brand; return this; }

    public String getBrand() { return brand; }

    @Override
    public String toString() { return getID(); }

}
