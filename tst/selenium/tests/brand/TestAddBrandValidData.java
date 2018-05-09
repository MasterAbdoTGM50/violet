package selenium.tests.brand;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAddBrandValidData extends WebDriverTestCase {

    @Test
    public void addBrandValidData() {

        userAction.login("Abdo", "Temsah");
        userAction.addBrand("Marmatos");

        Assert.assertEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);

    }

}
