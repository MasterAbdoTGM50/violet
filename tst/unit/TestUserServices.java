package unit;

import jonamatoka.violet.App;
import jonamatoka.violet.data.repo.UserRepository;
import jonamatoka.violet.web.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

@SpringBootTest(classes = App.class)
public class TestUserServices extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    private boolean register(String username, String email, String pass) {
        return userServices.register(username, email, pass).getBody();
    }

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

    @Test(dataProvider = "registerValidDataProvider")
    public void registerDuplicateData(String username, String email, String pass) {
        Assert.assertFalse(register(username, email, pass));
    }

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
