package selenium.tests.category;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAddCategoryValidData extends WebDriverTestCase {

    @Test
    public void addCategoryValidData() {

        userAction.login("Abdo", "Temsah");
        userAction.addCategory("Cats");

        Assert.assertEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);

    }

}
