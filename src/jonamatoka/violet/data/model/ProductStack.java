package jonamatoka.violet.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jonamatoka.violet.util.ITrackable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductStack implements ITrackable {

    private long stackId;

    private long productId;
    private double price;
    private int quantity;

    private int views;
    private int orders;

    public long getStackId() { return stackId; }

    public ProductStack setStackId(long stackId) { this.stackId = stackId; return this; }

    public long getProductId() { return productId; }

    public ProductStack setProductId(long productID) { this.productId = productID; return this; }

    public double getTotalPrice() { return getQuantity() * getPrice(); }

    public double getPrice() { return price; }

    public ProductStack setPrice(double price) { this.price = price; return this; }

    public int getQuantity() { return quantity; }

    public ProductStack setQuantity(int quantity) { this.quantity = quantity; return this; }

    @JsonProperty("views")
    @Override
    public int views() { return views; }

    @JsonProperty("orders")
    @Override
    public int orders() { return orders; }

    @Override
    public void view(int views) { this.views += views; }

    @Override
    public void order(int orders) { this.orders += orders; quantity -= orders; }

    @Override
    public String toString() { return "{ " + getProductId() + ":" + getQuantity() + ":" + getPrice() + " }"; }

}