package jonamatoka.violet.data.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private long transactionId;

    private String buyerId;
    private long storeId;
    private long stackId;
    private int quantity;
    private double price;

    @Enumerated(EnumType.STRING)
    protected State state = State.PENDING;

    public long getTransactionId() { return transactionId; }

    public Transaction setTransactionId(long transactionId) { this.transactionId = transactionId; return this; }

    public String getBuyerId() { return buyerId; }

    public Transaction setBuyerId(String buyerId) { this.buyerId = buyerId; return this; }

    public long getStoreId() { return storeId; }

    public Transaction setStoreId(long storeId) { this.storeId = storeId; return this; }

    public long getStackId() { return stackId; }

    public Transaction setStackId(long stackId) { this.stackId = stackId; return this; }

    public int getQuantity() { return quantity; }

    public Transaction setQuantity(int quantity) { this.quantity = quantity; return this; }

    public double getPrice() { return price; }

    public Transaction setPrice(double price) { this.price = price; return this; }

    public State getState() { return state; }

    public Transaction setState(State state) { this.state = state; return this; }

    public enum State {

        PENDING,
        COMPLETE

    }

}
