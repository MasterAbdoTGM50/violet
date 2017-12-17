package brand;

import jonamato.violet.product.brand.Brand;
import jonamato.violet.product.brand.Brands;
import org.dizitart.no2.exceptions.InvalidIdException;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestBrand {

    static private int NUM_OF_BRANDS = 100;
    static private int REMOVE_NUM = 10;
    static private int BRAND_NAME_MAX_SZ = 32;
    static private int BRAND_NAME_MIN_SZ = 5;

    private Brands brands;
    private Brand[] brand = new Brand[NUM_OF_BRANDS];

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

        int range = (BRAND_NAME_MAX_SZ - BRAND_NAME_MIN_SZ) + 1;
        for (int i = 0; i < NUM_OF_BRANDS; ++i) {

            brand[i] = new Brand();
            brand[i].setName(genRandName((int) (Math.random() * range) + BRAND_NAME_MIN_SZ));
        }
        brands = Brands.instance();
    }

    @Test
    public void addAndRemoveBrand() {

        System.out.println("Testing Brand Addition...");
        for (int i = 0; i < NUM_OF_BRANDS; ++i) {

            Assert.assertEquals(true, brands.add(brand[i]));
        }


        System.out.println("Asserting after addition...");
        System.out.println("Number of brands : " +  brands.all().size());

        Assert.assertEquals(NUM_OF_BRANDS, brands.all().size());


        System.out.println("Testing Remove Brand...");
        for (int i = 0; i < REMOVE_NUM; ++i) {

            brands.remove(brand[i]);
        }


        System.out.println("Asserting after removing...");
        System.out.println("Number of brands : " +  brands.all().size());

        Assert.assertEquals(NUM_OF_BRANDS - REMOVE_NUM, brands.all().size());
    }

    @Test(expectedExceptions = InvalidIdException.class)
    public void addBrand() {

        System.out.println("Brand name shouldn't be empty...");

        brand[0].setName("");
        Assert.assertEquals(false, brands.add(brand[0]));
    }

    @AfterTest
    public void done() {

        System.out.println(":)");
    }
}