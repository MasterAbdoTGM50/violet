package selenium.tests.category;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestAddCategoryInvalidData extends WebDriverTestCase{

    @DataProvider(name = "addCategoryInvalidDataProvider")
    public static Object[][] addCategoryInvalidDataProvider() {
        return new Object[][] { {" "}, {"or 1=1"}, {"&#163"}, {"<script>alert(\"Nayra\")</script>"}};
    }

    @Test(dataProvider = "addCategoryInvalidDataProvider")
    public void addCategoryInvalidData(String name) {
        userAction.login("Abdo", "Temsah");
        userAction.addCategory(name);

        /*TODO/Mourad: Redirect to Error Page*/
        Assert.assertNotEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);
    }

}
