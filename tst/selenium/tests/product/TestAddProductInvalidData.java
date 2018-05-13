package selenium.tests.product;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestAddProductInvalidData extends WebDriverTestCase {

    @DataProvider(name = "TestAddProductInvalidData")
    public static Object[][] addProductInvalidDataProvider() {
        return new Object[][] {
                {" ", " "},
                {"name", "description"},
                {"name", "description"},
                {"<script>alert(\"NAYRA\")</script>", "desc.."}
        };
    }

    @Test(dataProvider = "addProductInvalidDataProvider")
    public void addProductInvalidData(String name, String description) {
        userAction.login("Abdo", "Temsah");
        userAction.addProduct(name, description);

        /*TODO/Mourad: Redirect to Error Page*/
        Assert.assertNotEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);
    }

}
