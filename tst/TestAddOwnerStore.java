import jonamato.violet.account.Owner;
import jonamato.violet.store.Store;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestAddOwnerStore {
    private Owner owner, owner2, owner3;

    @BeforeMethod
    public void initialize() {

        System.out.println("initialize()");
        owner = new Owner();
        owner.setUsername("nayra");

        owner2 = new Owner();
        owner2.setUsername("adel");

        owner3 = new Owner();
        owner3.setUsername("xxx");
    }

    @Test
    public void testAddStores() {

        System.out.println("testAddStores");
        owner.add(new Store().setName("A"));
        owner.add(new Store().setName("B"));
        owner.add(new Store().setName("C"));
        owner2.add(new Store().setName("C"));
        owner2.add(new Store().setName("qqq"));
    }

    @AfterMethod
    public void checkStoreSize(){
        System.out.println("checkStoreSize()");
        Assert.assertEquals(3, owner.getStores().size());
        Assert.assertEquals(2, owner2.getStores().size());
        Assert.assertEquals(0, owner3.getStores().size());
    }

    @AfterTest
    public void clean(){

        System.out.println("clean()");
        owner2 = null;
        owner = null;
    }
}