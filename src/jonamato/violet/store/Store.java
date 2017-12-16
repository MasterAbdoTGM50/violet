package jonamato.violet.store;

import jonamato.violet.util.ITrackable;
import jonamato.violet.product.Cart;
import jonamato.violet.product.IProduct;
import jonamato.violet.product.ProductStack;
import org.dizitart.no2.objects.Id;

public class Store implements ITrackable {

    private final String type = this.getClass().getName();

    @Id
    private String name;
    private String ownerID;
    private String marketedProduct;
    private Cart inventory = new Cart();

    private int views;
    private int orders;

    public String getOwnerID() { return ownerID; }

    public Store setOwnerID(String ownerID) { this.ownerID = ownerID; return this; }

    public String getName() { return name; }

    public Store setName(String name) { this.name = name; return this; }

    public void add(ProductStack stack) {

        stack.setStoreID(this.getName());
        this.inventory.add(stack);

    }

    public void add(IProduct product) { this.inventory.add(product); }

    public void remove(IProduct product) { this.inventory.remove(product); }

    public String getMarketedProduct() { return this.marketedProduct; }

    public Store setMarketedProduct(String marketedProduct) { this.marketedProduct = marketedProduct; return this; }

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
