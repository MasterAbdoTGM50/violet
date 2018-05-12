package unit.services.user;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRegisterValidData extends TestUserServices {

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
        Assert.assertTrue(getUserServices().register(username, email, pass).getBody());
    }

}
