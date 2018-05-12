package selenium.tests.store;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestAddStoreInvalidData extends WebDriverTestCase {

    @DataProvider(name = "addStoreInvalidDataProvider")
    public static Object[][] addStoreInvalidDataProvider() {
        return new Object[][] {
                {" ", " ", " "},
                {"ZZZ", "ZZZ", "ZZZ"},
                {"ZZZ", "ZZZ", "ZZZ"},
                {"<script>alert(\"123\")</script>", "type", "addr.."}
        };
    }

    @Test(dataProvider = "addStoreInvalidDataProvider")
    public void addStoreInvalidData(String name, String type, String addr) {
        userAction.login("Abdo", "Temsah");
        userAction.addStore(name, type, addr);
        Assert.assertEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);
    }
}

