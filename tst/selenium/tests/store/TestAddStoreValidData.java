package selenium.tests.store;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAddStoreValidData extends WebDriverTestCase {

    @Test
    public void AddStoreValid() {
        userAction.login("Abdo", "Temsah");
        userAction.addStore("Store1", "outSide", "addr..");
        Assert.assertEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);
    }

}
