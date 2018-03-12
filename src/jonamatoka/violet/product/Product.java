package jonamatoka.violet.product;

import jonamatoka.violet.util.ITrackable;

import org.dizitart.no2.objects.Id;

public class Product implements ITrackable {

    @Id
    private String id;
    private String name;
    private Brand brand;
    private Category category;
    private String description;

    private int views;
    private int orders;

    public String getId() { return id; }

    public Product setId(String id) { this.id = id; return this; }

    public String getName() { return name; }

    public Product setName(String name) { this.name = name; return this; }

    public Brand getBrand() { return brand; }

    public Product setBrand(Brand brand) { this.brand = brand; return this; }

    public Category getCategory() { return category; }

    public Product setCategory(Category category) { this.category = category; return this; }

    public String getDescription() { return description; }

    public Product setDescription(String description) { this.description = description; return this; }

    @Override
    public int views() { return views; }

    @Override
    public int orders() { return orders; }

    @Override
    public void view(int views) { this.views += views; }

    @Override
    public void order(int orders) { this.orders += orders; }

    @Override
    public String toString() {
        return "ProductPages{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
