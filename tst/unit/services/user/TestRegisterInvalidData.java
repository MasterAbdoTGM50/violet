package unit.services.user;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestRegisterInvalidData extends TestVioletServices {

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
        Assert.assertFalse(register(username, email, pass));
    }

    
}
