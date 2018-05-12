package selenium.tests.category;

import selenium.Lib;
import selenium.tests.WebDriverTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestAddCategoryValidData extends WebDriverTestCase {

    @DataProvider(name = "addCategoryValidDataProvider")
    public static Object[][] addCategoryValidDataProvider() {
        return new Object[][] { {"Cats"}, {"_category"}, {"CATEGORY"}, {"cat_1"} };
    }

    @Test(dataProvider = "addCategoryValidDataProvider")
    public void addCategoryValidData(String name) {
        userAction.login("Abdo", "Temsah");
        userAction.addCategory(name);
        Assert.assertEquals(driver.getCurrentUrl(), Lib.Pages.ADMIN);
    }

}
