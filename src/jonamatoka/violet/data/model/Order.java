package jonamatoka.violet.data.model;

public class Order {

    private long storeId;
    private long productId;
    private String address;
    private int quantity;

    public Order() { }

    public Order(long storeId, long productId) {
        this.storeId = storeId;
        this.productId = productId;
    }

    public long getStoreId() { return storeId; }

    public void setStoreId(long storeId) { this.storeId = storeId; }

    public long getProductId() { return productId; }

    public void setProductId(long productId) { this.productId = productId; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
