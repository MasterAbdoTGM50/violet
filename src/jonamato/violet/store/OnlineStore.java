package jonamato.violet.store;

public class OnlineStore extends Store {

    @Override
    public OnlineStore setOwnerID(String ownerID) { return (OnlineStore)super.setOwnerID(ownerID); }

    @Override
    public OnlineStore setName(String name) { return (OnlineStore)super.setName(name); }

    @Override
    public OnlineStore setMarketedProduct(String marketedProduct) { return (OnlineStore)super.setMarketedProduct(marketedProduct); }

}
