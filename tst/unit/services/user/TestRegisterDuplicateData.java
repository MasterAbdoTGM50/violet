package unit.services.user;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRegisterDuplicateData extends AbstractTestUserService {

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
        getUserServices().register(username, email, pass);
        Assert.assertFalse(getUserServices().register(username, email, pass).getBody());
    }

}
