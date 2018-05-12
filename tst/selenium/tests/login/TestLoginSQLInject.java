package selenium.tests.login;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLoginSQLInject extends WebDriverTestCase {

    @Test
    public void loginSQLInject() {
        userAction.login("Nayra", "' or ' 1=1");
        Assert.assertEquals(driver.getCurrentUrl(), Lib.Pages.LOGIN);
    }

}
