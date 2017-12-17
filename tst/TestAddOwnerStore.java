import org.testng.Assert;
import org.testng.annotations.*;

import jonamato.violet.account.Owner;
import jonamato.violet.store.Store;

import java.util.ArrayList;
import java.util.List;

public class TestAddOwnerStore {

    static private int TEST_COUNT = 100;
    static private int MAX_STORE_COUNT = 200;

    static private int OWNER_NAME_MAX_SZ = 32;
    static private int STORE_NAME_MAX_SZ = 32;

    private List<Owner> owner = new ArrayList<>();
    private List<List<Store> > stores = new ArrayList<>();

    private int[] storeCountLog = new int[TEST_COUNT];

    // Random Name Generator
    String genRandName(int sz) {
        String ret = "";
        for (int i = 0; i < sz; ++i) {

            ret += "!\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".charAt((int)(Math.random() * sz));
        }
        return ret;
    }

    @BeforeMethod
    public void init() {
        System.out.println("Init...");
        for (int i = 0; i < TEST_COUNT; ++i) {
            owner.add(new Owner());
            owner.get(i).setUsername(genRandName((int) (Math.random() * OWNER_NAME_MAX_SZ) + 1));
        }
    }

    @Test
    public void TestAddStores() {
        System.out.println("Testing Store Addition...");
        for (int i = 0; i < TEST_COUNT; ++i) {
            storeCountLog[i] = (int)(Math.random() * MAX_STORE_COUNT) + 1;
            stores.add(new ArrayList<>());
            for (int j = 0; j < storeCountLog[i]; ++j) {
                stores.get(i).add(new Store().setName(genRandName((int)(Math.random() * STORE_NAME_MAX_SZ) + 1)));
                owner.get(i).add(stores.get(i).get(j));
            }
        }

        System.out.println("Asserting owner's store count...");
        for (int i = 0; i < TEST_COUNT; ++i) {
            Assert.assertEquals(storeCountLog[i], owner.get(i).getStores().size());
            System.out.println(storeCountLog[i]);
        }
    }

    @Test
    public void TestRemoveStores() {
        System.out.println("Testing Remove Store...");
        for (int i = 0; i < TEST_COUNT; ++i) {
            owner.get(i).remove(stores.get(i).get(0));
            storeCountLog[i] = storeCountLog[i] - 1;
        }

        System.out.println("Asserting owner's store count after removing...");
        for (int i = 0; i < TEST_COUNT; ++i) {
            Assert.assertEquals(storeCountLog[i], owner.get(i).getStores().size());
            System.out.println(storeCountLog[i]);
        }
    }

    @AfterTest
    public void done() {

        System.out.println(":)");
    }
}