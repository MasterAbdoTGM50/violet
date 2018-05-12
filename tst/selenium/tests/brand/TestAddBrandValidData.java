package selenium.tests.brand;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestAddBrandValidData extends WebDriverTestCase {

    @DataProvider(name = "addBrandValidDataProvider")
    public static Object[][] addBrandValidDataProvider() {
        return new Object[][] { {"Marmatos"}, {"_brand"}, {"BRAND"} };
    }

    @Test(dataProvider = "addBrandValidDataProvider")
    public void addBrandValidData(String name) {
        userAction.login("Abdo", "Temsah");
        userAction.addBrand(name);
        Assert.assertEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);
    }

}
