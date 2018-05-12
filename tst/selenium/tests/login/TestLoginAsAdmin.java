package selenium.tests.login;;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLoginAsAdmin extends WebDriverTestCase {

    @Test
    public void loginAsAdmin() {
        userAction.login("Abdo", "Temsah");
        userAction.clickHome();
        Assert.assertEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);
    }

}