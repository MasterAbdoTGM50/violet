package jonamatoka.violet.product;

import org.dizitart.no2.objects.Id;

public class Product {

    @Id
    private String id;
    private String name;
    private String category;
    private String brand;
    private String description;

    public String getId() { return id; }

    public Product setId(String id) { this.id = id; return this; }

    public String getName() { return name; }

    public Product setName(String name) { this.name = name; return this; }

    public String getCategory() { return category; }

    public Product setCategory(String category) { this.category = category; return this; }

    public String getBrand() { return brand; }

    public Product setBrand(String brand) { this.brand = brand; return this; }

    public String getDescription() { return description; }

    public Product setDescription(String description) { this.description = description; return this; }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}