package unit.services.user;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestRegisterDuplicateData extends TestVioletServices {

    @DataProvider(name = "registerDuplicateDataProvider")
    public Object[][] registerDuplicateDataProvider() {
        return new Object[][] {
                {"Shehab", "shehab@gmail.com", "shehab"},
                {"Reham", "reham@gmail.com", "reham"},
                {"Shimaa", "shimaa@gmail.com", "shimaa"}
        };
    }

    @Test(dataProvider = "registerDuplicateDataProvider")
    public void registerDuplicateData(String username, String email, String pass) {
        register(username, email, pass);
        Assert.assertFalse(register(username, email, pass));
    }

}
