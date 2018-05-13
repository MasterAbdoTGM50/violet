package selenium.tests.brand;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestAddBrandInvalidData extends WebDriverTestCase {

    @DataProvider(name = "addBrandInvalidDataProvider")
    public static Object[][] addBrandInvalidDataProvider() {
        return new Object[][] { {" "}, {"or 1=1"}, {"&#165"}, {"<script>alert(\"123\")</script>"} };
    }

    @Test(dataProvider = "addBrandInvalidDataProvider")
    public void addBrandInvalidData(String name) {
        userAction.login("Abdo", "Temsah");
        userAction.addBrand(name);

        /*TODO/Mourad: Redirect to Error Page*/
        Assert.assertNotEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);
    }

}
