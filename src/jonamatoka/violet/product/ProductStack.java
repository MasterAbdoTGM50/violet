package jonamatoka.violet.product;

import jonamatoka.violet.util.ITrackable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class ProductStack implements ITrackable {

    private long productId;
    private String productName;
    private double price;
    private int quantity;

    private int views;
    private int orders;

    public long getProductId() { return productId; }

    public ProductStack setProductId(long productID) { this.productId = productID; return this; }

    public String getProductName() { return productName; }

    public ProductStack setProductName(String productName) {

        this.productName = productName;
        return this;

    }

    public double getTotalPrice() { return getQuantity() * getPrice(); }

    public double getPrice() { return price; }

    public ProductStack setPrice(double price) { this.price = price; return this; }

    public int getQuantity() { return quantity; }

    public ProductStack setQuantity(int quantity) { this.quantity = quantity; return this; }

    @Override
    public int views() { return views; }

    @Override
    public int orders() { return orders; }

    @Override
    public void view(int views) { this.views += views; }

    @Override
    public void order(int orders) { this.orders += orders; quantity -= orders; }

    @Override
    public String toString() { return getProductName() + "\t" + getQuantity() + "\t" + getPrice() + "$"; }

}