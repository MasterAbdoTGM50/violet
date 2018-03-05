package jonamatoka.violet.store;

import org.dizitart.no2.objects.Id;

public class Store {

    @Id
    private String id;
    private String ownerId;
    private String name;
    private String type;
    private String address;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getOwnerId() { return ownerId; }

    public void setOwnerId(String ownerID) { this.ownerId = ownerID; }

    public String getName() { return name; }

    public Store setName(String name) { this.name = name; return this; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}