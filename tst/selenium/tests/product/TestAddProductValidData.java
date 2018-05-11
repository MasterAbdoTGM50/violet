package selenium.tests.product;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestAddProductValidData extends WebDriverTestCase{

    @DataProvider(name = "TestAddProductValidData")
    public static Object[][] addProductValidDataProvider() {

        return new Object[][] { {"_Product", "product.."}, {"PRODUCT", "DESCRIPTION"} };

    }

    @Test(dataProvider = "addProductValidDataProvider")
    public void addProductValidData(String name, String description) {

        userAction.login("Abdo", "Temsah");

        userAction.addProduct(name, description);

        Assert.assertEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);
    }
}
