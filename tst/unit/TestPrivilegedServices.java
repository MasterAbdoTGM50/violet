package unit;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.User;
import jonamatoka.violet.data.repo.UserRepository;

import net.openhft.hashing.LongHashFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

public abstract class TestPrivilegedServices extends AbstractTestNGSpringContextTests {

    protected Map<Integer, User> user = new HashMap<>();

    @Autowired
    private UserRepository userRepository;

    @BeforeClass
    public void setup() {
        User testAdmin = new User()
                .setUsername("TestAdmin")
                .setEmail("TestAdmin@violet.com")
                .setHash(LongHashFunction.xx().hashChars("TestAdmin")).setPriviliges(Lib.Privileges.ADMIN);

        user.put(Lib.Privileges.ADMIN, testAdmin);
        userRepository.save(testAdmin);

        User testOwner = new User()
                .setUsername("TestOwner")
                .setEmail("TestOwner@violet.com")
                .setHash(LongHashFunction.xx().hashChars("TestOwner")).setPriviliges(Lib.Privileges.OWNER);

        user.put(Lib.Privileges.OWNER, testOwner);
        userRepository.save(testOwner);

        User testUser = new User()
                .setUsername("TestUser")
                .setEmail("TestUser@violet.com")
                .setHash(LongHashFunction.xx().hashChars("TestUser")).setPriviliges(Lib.Privileges.USER);

        user.put(Lib.Privileges.USER, testUser);
        userRepository.save(testUser);
    }

}
