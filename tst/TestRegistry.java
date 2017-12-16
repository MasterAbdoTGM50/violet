import jonamato.violet.account.*;
import org.dizitart.no2.exceptions.InvalidIdException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRegistry {

    private Registry registry;
    private User user;
    String pass;

    @BeforeMethod
    public void initialize() {

        registry = Registry.instance();
        user = new Admin();

        user.setUsername("salma");
        user.setEmail("s@gmail.com");

        pass = "222";
    }

    @DataProvider(name = "register")
    public Object[][] register() {

        System.out.println("register1");
        return new Object[][] {
                { true, new Admin().setUsername("nayra").setEmail("n.@gmail.com"), "123" },
                { false, new Admin().setUsername("nayra").setEmail("n.@gmail.com"), "123" },
                { true, new Owner().setUsername("c").setEmail("c@gmail.com"), "abc" },
                { true, new Buyer().setUsername("xxx").setEmail("c@gmail.com"), "abc" },
        };
    }

    @Test(dataProvider = "register")
    public void testRegister1(boolean expectedResult, User u, String s) {

        System.out.println("testRegister1()");

        System.out.println(u.getUsername() + " ?register => " + true);
        Assert.assertEquals(expectedResult, registry.register(u, s));
    }

    @Test(expectedExceptions = InvalidIdException.class)
    public void testRegister3() {

        System.out.println("testRegister3()");

        // user name is empty :)
        user.setUsername("");
        Assert.assertEquals(false, registry.register(user, pass));
    }

    @Test(priority = 1)
    public void testLogin() {

        System.out.println("testLogin()");

        Assert.assertEquals("c@gmail.com", registry.login("c", "abc").getEmail());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testLogin2() {

        // not found
        System.out.println("testLogin2()");

        Assert.assertEquals("q@gmail.com", registry.login("q", "111").getEmail());
    }

    @Test(priority = 2)
    public void testListOfAllUsers() {

        System.out.println("testListOfAllUsers()");
        Assert.assertEquals(3, registry.all().size());
    }

    @AfterMethod
    public void clean() {

        registry = null;
        user = null;
    }
}