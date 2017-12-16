import jonamato.violet.product.Product;
import jonamato.violet.product.Products;
import jonamato.violet.product.brand.Brands;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class TestProduct {

    private Products products;
    private Brands brands;
    private List<Product> list, list2;

    @BeforeMethod
    public void initialize() {

        products = Products.instance();
        brands =  Brands.instance();
        list = new ArrayList<>();
        list2 = new ArrayList<>();
    }

    @DataProvider(name = "addProduct")
    public Object[][] addProduct() {

        System.out.println("addProduct");
        return new Object[][] {
                { true, new Product().setName("PC").setId("1").setBrand("AAA").setCategory("BBB").setDescription("PC") },
                { true, new Product().setName("Mobile").setId("2").setBrand("QQQ").setCategory("BBB").setDescription("Mobile") },
                { true, new Product().setName("TV").setId("3").setBrand("WWW").setCategory("BBB").setDescription("TV") },
                { true, new Product().setName("TV").setId("4").setBrand("AAA").setCategory("BBB").setDescription("TV") },
        };
    }

    @Test(dataProvider = "addProduct")
    public void testAddProduct(boolean expectedResult, Product p) {

        System.out.println("testAddProduct()");
        Assert.assertEquals(expectedResult, products.add(p));
    }

    @Test(priority = 2)
    public void testListOfAllProducts() {

        System.out.println("testListOfAllProducts()");
        Assert.assertEquals(4, products.all().size());

    }

    @Test(priority = 3)
    public void testshowAllProducts() throws Exception{

        System.out.println("testshowAllProducts()");
        list.add(new Product().setName("PC").setId("1").setBrand("AAA").setCategory("BBB").setDescription("PC"));
        list.add(new Product().setName("Mobile").setId("2").setBrand("QQQ").setCategory("BBB").setDescription("Mobile"));
        list.add(new Product().setName("TV").setId("3").setBrand("WWW").setCategory("BBB").setDescription("TV"));
        list.add(new Product().setName("TV").setId("4").setBrand("AAA").setCategory("BBB").setDescription("TV"));

        list2 = products.all();

        for(int i=0; i < list.size(); i++){
            Assert.assertEquals(list.get(i).getName(), list2.get(i).getName());
            System.out.println("Actual: " + list.get(i).getID() + " " +
                                            list.get(i).getName() + " " +
                                            list.get(i).getBrand() + " " +
                                            list.get(i).getCategory()
            );

            System.out.println("Expected: " + list2.get(i).getID() + " " +
                                              list2.get(i).getName() + " " +
                                              list2.get(i).getBrand() + " " +
                                              list2.get(i).getCategory()
            );
        }
    }

    @AfterMethod
    public void clean() {

        products = null;
    }
}