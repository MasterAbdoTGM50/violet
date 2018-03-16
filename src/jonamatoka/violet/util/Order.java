package jonamatoka.violet.util;

public class Order {

    private String storeId;
    private String productId;
    private String address;
    private int quantity;

    public Order() { }

    public Order(String storeId, String productId) {
        this.storeId = storeId;
        this.productId = productId;
    }

    public String getStoreId() { return storeId; }

    public void setStoreId(String storeId) { this.storeId = storeId; }

    public String getProductId() { return productId; }

    public void setProductId(String productId) { this.productId = productId; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
