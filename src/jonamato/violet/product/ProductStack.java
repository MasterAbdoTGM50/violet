package jonamato.violet.product;

import jonamato.violet.util.ITrackable;

import java.util.Date;

public class ProductStack implements ITrackable {

    private String productID;
    private String productName;
    private double price;
    private int quantity;
    private String storeID;
    private Date date;

    private int views;
    private int orders;

    public String getProductID() { return productID; }

    public ProductStack setProductID(String productID) { this.productID = productID; return this; }

    public String getProductName() { return productName; }

    public ProductStack setProductName(String productName) {

        this.productName = productName;
        return this;

    }

    public double getPrice() { return price; }

    public ProductStack setPrice(double price) { this.price = price; return this; }

    public int getQuantity() { return quantity; }

    public ProductStack setQuantity(int quantity) { this.quantity = quantity; return this; }

    public String getStoreID() { return storeID; }

    public ProductStack setStoreID(String storeID) { this.storeID = storeID; return this; }

    public Date getDate() { return date; }

    public ProductStack setDate(Date date) { this.date = date; return this; }

    @Override
    public int views() { return views; }

    @Override
    public int orders() { return orders; }

    @Override
    public void view(int views) { this.views += views; }

    @Override
    public void order(int orders) { this.orders += orders; quantity -= orders; }

    public double getTotalPrice() { return getQuantity() * getPrice(); }

    @Override
    public String toString() { return getProductName() + "\t" + getQuantity() + "\t" + getPrice() + "$"; }

}
