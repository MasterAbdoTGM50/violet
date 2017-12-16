package jonamato.violet.store;

import jonamato.violet.util.Address;

public class OnsiteStore extends Store {

    private Address address;

    @Override
    public OnsiteStore setOwnerID(String ownerID) { return (OnsiteStore)super.setOwnerID(ownerID); }

    @Override
    public OnsiteStore setName(String name) { return (OnsiteStore)super.setName(name); }

    @Override
    public OnsiteStore setMarketedProduct(String marketedProduct) { return (OnsiteStore)super.setMarketedProduct(marketedProduct); }

    public Address getAddress() { return address; }

    public OnsiteStore setAddress(Address address) { this.address = address; return this; }

}
