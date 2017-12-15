package store;

import misc.ITrackable;
import product.Cart;
import product.IProduct;
import product.ProductStack;

public interface IStore extends ITrackable {

    String getID();

    String getOwnerID();

    String getName();

    void add(ProductStack stack);

    void add(IProduct product);

    void remove(IProduct product);

    String getMarketedProduct();

    Cart getInventory();

}
