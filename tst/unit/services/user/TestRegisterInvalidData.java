package unit.services.user;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRegisterInvalidData extends AbstractTestUserService {

    @DataProvider(name = "registerInvalidDataProvider")
    public Object[][] registerInvalidDataProvider() {
        return new Object[][] {
                {" ", " @gmail.com", "/"},
                {"!= ", "reham@./...com", "or '1=1"},
                {"Shimaa", "s@gmail.999", " "}
        };
    }

    @Test(dataProvider = "registerInvalidDataProvider")
    public void registerInvalidData(String username, String email, String pass) {
        Assert.assertFalse(getUserServices().register(username, email, pass).getBody());
    }

    
}
