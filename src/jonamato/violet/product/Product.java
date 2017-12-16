package jonamato.violet.product;

import org.dizitart.no2.objects.Id;

public class Product implements IProduct {

    @Id
    private String id;
    private String name;
    private String category;
    private String brand;
    private String description;

    private int views;
    private int orders;

    @Override
    public String getID() { return id; }

    public Product setId(String id) { this.id = id; return this; }

    @Override
    public String getName() { return name; }

    public Product setName(String name) { this.name = name; return this; }

    @Override
    public String getCategory() { return category; }

    public Product setCategory(String category) { this.category = category; return this; }

    @Override
    public String getBrand() { return brand; }

    public Product setBrand(String brand) { this.brand = brand; return this; }

    @Override
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
    public String toString() { return name; }

}
