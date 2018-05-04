package jonamatoka.violet.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jonamatoka.violet.util.ITrackable;
import jonamatoka.violet.util.action.StoreAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store implements ITrackable {

    @Id
    @GeneratedValue
    private long storeId;
    private String ownerId;
    private String name;
    private String type;
    private String address;

    @ElementCollection
    private List<String> collaborators = new ArrayList<>();

    @Embedded
    @JsonUnwrapped
    private Cart inventory = new Cart();

    @ElementCollection
    private List<Offer> offers = new ArrayList<>();

    @OneToMany
    private List<StoreAction> actions = new ArrayList<>();

    private int views;
    private int orders;

    public long getStoreId() { return storeId; }

    public void setStoreId(long storeId) { this.storeId = storeId; }

    public String getOwnerId() { return ownerId; }

    public void setOwnerId(String ownerID) { this.ownerId = ownerID; }

    public String getName() { return name; }

    public Store setName(String name) { this.name = name; return this; }

    public String getType() { return type; }

    public Store setType(String type) { this.type = type; return this; }

    public String getAddress() { return address; }

    public Store setAddress(String address) { this.address = address; return this; }

    public List<String> getCollaborators() { return collaborators; }

    public Cart getInventory() { return inventory; }

    public List<Offer> getOffers() { return offers; }

    @JsonIgnore
    public List<StoreAction> getActions() { return this.actions; }

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