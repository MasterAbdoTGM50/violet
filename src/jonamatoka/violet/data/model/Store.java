package jonamatoka.violet.data.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jonamatoka.violet.util.ITrackable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store implements ITrackable {

    @Id
    @GeneratedValue
    private long id;
    private String ownerId;
    private String name;
    private String type;
    private String address;

    @ElementCollection
    private List<String> collaborators = new ArrayList<>();

    @Embedded
    @JsonUnwrapped
    private Cart inventory = new Cart();

    private int views;
    private int orders;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getOwnerId() { return ownerId; }

    public void setOwnerId(String ownerID) { this.ownerId = ownerID; }

    public String getName() { return name; }

    public Store setName(String name) { this.name = name; return this; }

    public String getType() { return type; }

    public Store setType(String type) { this.type = type; return this; }

    public String getAddress() { return address; }

    public Store setAddress(String address) { this.address = address; return this; }

    public void add(Product product) { this.inventory.add(product); }

    public void remove(Product product) { this.inventory.remove(product); }

    public List<String> getCollaborators() { return collaborators; }

    public Cart getInventory() { return inventory; }

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
        return "StorePages{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}