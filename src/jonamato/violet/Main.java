package jonamato.violet;

import jonamato.violet.account.Admin;
import jonamato.violet.account.Buyer;
import jonamato.violet.account.Owner;
import jonamato.violet.account.Registry;
import jonamato.violet.product.Product;
import jonamato.violet.product.ProductStack;
import jonamato.violet.product.Products;
import jonamato.violet.product.brand.Brand;
import jonamato.violet.product.brand.Brands;
import jonamato.violet.product.category.Categories;
import jonamato.violet.product.category.Category;
import jonamato.violet.store.OnlineStore;
import jonamato.violet.store.Stores;
import jonamato.violet.view.ActionWindow;
import jonamato.violet.view.App;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Lib.Platform.persist();

        App app = new App();
        app.start(new ActionWindow(app, null).build());

        Lib.Platform.commit();

    }

}
