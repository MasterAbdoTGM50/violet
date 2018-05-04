package jonamatoka.violet.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jonamatoka.violet.util.ITrackable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product implements ITrackable {

    @Id
    @GeneratedValue
    private long productId;
    private String name;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Category category;
    private String description;

    private int views;
    private int orders;

    public long getProductId() { return productId; }

    public Product setProductId(long productId) { this.productId = productId; return this; }

    public String getName() { return name; }

    public Product setName(String name) { this.name = name; return this; }

    public Brand getBrand() { return brand; }

    public Product setBrand(Brand brand) { this.brand = brand; return this; }

    public Category getCategory() { return category; }

    public Product setCategory(Category category) { this.category = category; return this; }

    public String getDescription() { return description; }

    public Product setDescription(String description) { this.description = description; return this; }

    @JsonProperty("views")
    @Override
    public int views() { return views; }

    @JsonProperty("orders")
    @Override
    public int orders() { return orders; }

    @Override
    public void view(int views) { this.views += views; }

    @Override
    public void order(int orders) { this.orders += orders; }

}
