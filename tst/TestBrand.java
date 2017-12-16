import jonamato.violet.product.brand.Brand;
import jonamato.violet.product.brand.Brands;
import org.dizitart.no2.exceptions.InvalidIdException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestBrand {

    private Brands brands;
    private Brand brand;

    @BeforeMethod
    public void initialize() {

        System.out.println("initialize()");
        brands = Brands.instance();
        brand = new Brand();
    }

    @DataProvider(name = "addBrand")
    public Object[][] addBrand() {

        System.out.println("addBrand()");
        return new Object[][] {
                { true, new Brand().setName("Walton") },
                { false, new Brand().setName("Walton") },
                { true, new Brand().setName("Clevo") },
                { true, new Brand().setName("Doel") },
        };
    }

    @Test(dataProvider = "addBrand")
    public void testAddBrand(boolean expectedResult, Brand b) {

        System.out.println("testAddBrand()");

        System.out.println(b.getName() + " ?add => " + expectedResult);
        Assert.assertEquals(expectedResult, brands.add(b));
    }

    @Test(expectedExceptions = InvalidIdException.class)
    public void testAddBrand2() {

        System.out.println("testAddBrand2()");

        // brand name is empty :)
        brand.setName("");
        Assert.assertEquals(false, brands.add(brand));
    }

    @Test(priority = 2)
    public void testListOfAllBrands() {

        System.out.println("testListOfAllBrands()");
        Assert.assertEquals(3, brands.all().size());
    }

    @AfterMethod
    public void clean() {

        brand = null;
        brands = null;
    }
}