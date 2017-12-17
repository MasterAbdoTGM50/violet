import jonamato.violet.account.*;
import org.dizitart.no2.exceptions.InvalidIdException;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestRegistry {


    private Registry registry;
    private User user;
    String pass;

    @BeforeMethod
    public void initialize() {

        registry = Registry.instance();
        user = new Admin();

        user.setUsername("Youssuf");
        user.setEmail("y@gmail.com");

        pass = "222";
    }

    @DataProvider(name = "register")
    public Object[][] register() {

        System.out.println("register1");
        return new Object[][] {
                { true, new Admin().setUsername("nayra").setEmail("n.@gmail.com"), "123" },
                { false, new Admin().setUsername("nayra").setEmail("n.@gmail.com"), "123" },
                { true, new Owner().setUsername("Abd- El Rahman").setEmail("a@gmail.com"), "abc" },
                { true, new Buyer().setUsername("Mourad").setEmail("m@gmail.com"), "xxx" },
        };
    }

    @Test(dataProvider = "register")
    public void testRegister1(boolean expectedResult, User u, String s) {

        System.out.println(u.getUsername() + " ?register => " + expectedResult);
        Assert.assertEquals(expectedResult, registry.register(u, s));
    }

    @Test(expectedExceptions = InvalidIdException.class)
    public void testRegister3() {

        System.out.println("Register with empty user name...");

        user.setUsername("");
        Assert.assertEquals(false, registry.register(user, pass));
    }

    @Test(priority = 2)
    public void testLogin() {

        System.out.println("Login...");
        Assert.assertEquals("a@gmail.com", registry.login("Abd- El Rahman", "abc").getEmail());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testLogin2() {

        System.out.println("Login User not found...");
        Assert.assertEquals("q@gmail.com", registry.login("q", "111").getEmail());
    }

    @Test(priority = 1)
    public void remove() {

        System.out.println("Remove...");
        registry.register(user, pass);

        System.out.println(user.getUsername() + " ?register => " + true);

        Assert.assertEquals(4, registry.all().size());
        System.out.println("before remove user : " + registry.all().size());

        Assert.assertEquals(true, registry.remove(user));

        Assert.assertEquals(3, registry.all().size());
        System.out.println("after remove user : " + registry.all().size());
    }
}