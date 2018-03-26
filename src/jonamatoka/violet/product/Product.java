package jonamatoka.violet.product;

import jonamatoka.violet.util.ITrackable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product implements ITrackable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Brand brand;
    private Category category;
    private String description;

    private int views;
    private int orders;

    public long getId() { return id; }

    public Product setId(long id) { this.id = id; return this; }

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
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
