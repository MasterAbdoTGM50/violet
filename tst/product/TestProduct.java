package product;

import jonamato.violet.product.Product;
import jonamato.violet.product.Products;
import jonamato.violet.product.brand.Brand;
import jonamato.violet.product.brand.Brands;
import org.dizitart.no2.exceptions.InvalidIdException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

public class TestProduct {

    static private int NUM_OF_PRODUCTS = 100;
    static private int REMOVE_NUM = 10;
    static private int PRODUCT_NAME_MAX_SZ = 32;
    static private int PRODUCT_NAME_MIN_SZ = 5;

    private Products products;
    private Product[] product = new Product[NUM_OF_PRODUCTS];

    // Random Name Generator
    String genRandName(int sz) {
        String ret = "";
        for (int i = 0; i < sz; ++i) {

            ret += "!\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".charAt((int) (Math.random() * sz));
        }
        return ret;
    }

    @BeforeMethod
    public void init() {
        System.out.println("Init...");

        products = Products.instance();
        for (int i = 0; i < NUM_OF_PRODUCTS; ++i) {

            product[i] = new Product();

            int range = (PRODUCT_NAME_MAX_SZ - PRODUCT_NAME_MIN_SZ) + 1;
            product[i].setID(i + "");
            product[i].setName(genRandName((int) (Math.random() * range) + PRODUCT_NAME_MIN_SZ));
            product[i].setBrand(genRandName((int) (Math.random() * range) + PRODUCT_NAME_MIN_SZ));
        }
    }

    @Test(priority = 1)
    public void addAndRemoveProduct() {

        System.out.println("Testing Product Addition...");
        for (int i = 0; i < NUM_OF_PRODUCTS; ++i) {

            Assert.assertEquals(true, products.add(product[i]));
        }


        System.out.println("Asserting after addition...");
        System.out.println("Number of products : " +  products.all().size());

        Assert.assertEquals(NUM_OF_PRODUCTS, products.all().size());


        System.out.println("Testing Remove Product...");
        for (int i = 0; i < REMOVE_NUM; ++i) {

            products.remove(product[i]);
        }


        System.out.println("Asserting after removing...");
        System.out.println("Number of products : " +  products.all().size());

        Assert.assertEquals(NUM_OF_PRODUCTS - REMOVE_NUM, products.all().size());
    }

    @Test(priority = 2)
    public void updateProduct() {

        System.out.println("Testing update Product...");

        Product p = new Product();
        p.setName(product[0].getName());

        product[0].setName("TOFI");

        Assert.assertEquals(true, products.update(product[0]));
        Assert.assertNotEquals(p.getName(), products.all().get(0).getName());

        System.out.println("Before update : " + p.getName());
        System.out.println("After update : " + product[0].getName());

    }

    @AfterTest
    public void done() {

        System.out.println(":)");
    }
}