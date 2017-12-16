package jonamato.violet;

import jonamato.violet.account.Admin;
import jonamato.violet.account.Owner;
import jonamato.violet.account.Registry;
import jonamato.violet.product.Product;
import jonamato.violet.product.Products;
import jonamato.violet.product.brand.Brand;
import jonamato.violet.product.brand.Brands;
import jonamato.violet.product.category.Categories;
import jonamato.violet.product.category.Category;
import jonamato.violet.view.ActionWindow;
import jonamato.violet.view.App;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Registry.instance().register(new Admin().setUsername("admin1").setEmail("admin1@violet.org"), "jonamato");
        Registry.instance().register(new Owner().setUsername("owner1").setEmail("owner1@violet.org"), "jonamato");

        Brands.instance().add(new Brand().setName("Temsah"));
        Categories.instance().add(new Category().setName("Lap"));
        Products.instance().add(new Product().setId("p1").setName("name").setCategory("Lap").setBrand("Temsah"));

        App app = new App();
        app.start(new ActionWindow(app));

    }

}
