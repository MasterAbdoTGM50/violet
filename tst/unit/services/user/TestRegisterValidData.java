package unit.services.user;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestRegisterValidData extends TestVioletServices {

    @DataProvider(name = "registerValidDataProvider")
    public Object[][] registerValidDataProvider() {
        return new Object[][] {
                {"Shehab", "shehab@gmail.com", "shehab"},
                {"Reham", "reham@gmail.com", "reham"},
                {"Shimaa", "shimaa@gmail.com", "shimaa"}
        };
    }

    @Test(dataProvider = "registerValidDataProvider")
    public void registerValidData(String username, String email, String pass) {
        Assert.assertTrue(register(username, email, pass));
    }

}
