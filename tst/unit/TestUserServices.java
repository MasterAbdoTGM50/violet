package unit;

import jonamatoka.violet.data.model.User;

import jonamatoka.violet.data.repo.UserRepository;
import jonamatoka.violet.web.services.UserServices;

import net.openhft.hashing.LongHashFunction;

import org.mockito.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class TestUserServices {

    private List<User> users = new ArrayList<>();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServices userServices;

    @BeforeTest
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userRepository = Mockito.mock(UserRepository.class);
        userServices = new UserServices(userRepository);
    }

    private Object register(String username, String email, String pass) {
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
            Assert.assertEquals(register(username, email, pass), true);

            User user = new User()
                .setUsername(username)
                .setEmail(email)
                .setHash(LongHashFunction.xx().hashChars(pass)).setPriviliges(6);

            users.add(user);
            when(userRepository.findOne(username)).thenReturn(user);
            when(userRepository.exists(username)).thenReturn(true);
    }

    @Test(dataProvider = "registerValidDataProvider")
    public void registerDuplicateData(String username, String email, String pass) {
        Assert.assertEquals(register(username, email, pass), false);
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
        Assert.assertEquals(register(username, email, pass), false);
    }

}
