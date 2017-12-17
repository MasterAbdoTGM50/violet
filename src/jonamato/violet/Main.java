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

        Admin admin = new Admin().setUsername("admin1").setEmail("admin1@violet.org");
        Owner owner1 = new Owner().setUsername("owner1").setEmail("owner1@violet.org").setPremium(true);
        Owner owner2 = new Owner().setUsername("owner2").setEmail("owner1@violet.org");
        Buyer buyer1 = new Buyer().setUsername("buyer1").setEmail("buyer1@violet.org");

        OnlineStore store = new OnlineStore().setName("Mega Store");

        Product product = new Product()
                .setID("p1")
                .setName("A Testy Prooduct")
                .setDescription("A nice description\nAnd a new line!")
                .setBrand("Temsah")
                .setCategory("Tofi");

        ProductStack stack = new ProductStack()
                .setProductID(product.getID())
                .setProductName(product.getName())
                .setPrice(40)
                .setQuantity(10);

        Registry.instance().register(admin, "jonamato");
        Registry.instance().register(owner1, "jonamato");
        Registry.instance().register(owner2, "jonamato");
        Registry.instance().register(buyer1, "jonamato");

        Products.instance().add(product);

        Stores.instance().add(owner1, store);
        Stores.instance().add(store, stack);

        Brands.instance().add(new Brand().setName("Temsah"));
        Categories.instance().add(new Category().setName("Tofi"));

        App app = new App();
        app.start(new ActionWindow(app, null).build());

    }

}
