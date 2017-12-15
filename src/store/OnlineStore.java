package store;

import misc.Address;
import org.dizitart.no2.objects.Id;
import product.Cart;
import product.IProduct;
import product.ProductStack;

public class OnlineStore implements IStore {

    @Id
    private String id;
    private String ownerID;
    private String name;
    private String marketedProduct;
    private Cart inventory = new Cart();

    private int views;
    private int orders;

    @Override
    public String getID() { return id; }

    public OnlineStore setId(String id) { this.id = id; return this; }

    @Override
    public String getOwnerID() { return ownerID; }

    public OnlineStore setOwnerID(String ownerID) { this.ownerID = ownerID; return this; }

    @Override
    public String getName() { return name; }

    public OnlineStore setName(String name) { this.name = name; return this; }

    @Override
    public void add(ProductStack stack) { this.inventory.add(stack); }

    @Override
    public void add(IProduct product) { this.inventory.add(product); }

    @Override
    public void remove(IProduct product) { this.inventory.remove(product); }

    @Override
    public String getMarketedProduct() { return this.marketedProduct; }

    public OnlineStore setMarketedProduct(String marketedProduct) { this.marketedProduct = marketedProduct; return this; }

    @Override
    public Cart getInventory() { return inventory; }

    @Override
    public int views() { return views; }

    @Override
    public int orders() { return orders; }

    @Override
    public void view(int views) { this.views += views; }

    @Override
    public void order(int orders) { this.orders += orders; }

}
