package unit;

import jonamatoka.violet.App;
import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.data.model.ProductStack;
import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.model.User;
import jonamatoka.violet.data.repo.StoreRepository;

import jonamatoka.violet.data.repo.UserRepository;
import jonamatoka.violet.web.services.StoreServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.testng.Assert;
import org.testng.annotations.*;

@SpringBootTest(classes = App.class)
public class TestStoreServices extends TestPrivilegedServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreServices storeServices;

    private boolean add(String username, Store store) {
        return storeServices.add(store, username).getBody();
    }

    @DataProvider(name = "storeValidDataProvider")
    public Object[][] storeValidDataProvider() {
        return new Object[][] {
                {"Store_1", "Onsite", "Some St."}
        };
    }

    @Test(dataProvider = "storeValidDataProvider")
    public void addValidData(String name, String type, String address) {
        Store store = new Store().setName(name).setType(type).setAddress(address);
        Assert.assertTrue(add(user.get(Lib.Privileges.ADMIN).getUsername(), store));
    }

    @Test(dataProvider = "storeValidDataProvider", dependsOnMethods = "addValidData")
    public void addDuplicateData(String name, String type, String address) {
        Store store = new Store().setName(name).setType(type).setAddress(address);
        Assert.assertFalse(add(user.get(Lib.Privileges.ADMIN).getUsername(), store));
    }

    @DataProvider(name = "storeInvalidDataProvider")
    public Object[][] storeInvalidDataProvider() {
        return new Object[][] {
                {"Store_1", "Onsite", "Some St."},
                {" ", "Onsite", "Some St."},
                {"Store_1", " ", "Some St."},
                {"Store_1", "Onsite", "  "}
        };
    }

    @Test(dataProvider = "storeInvalidDataProvider")
    public void addInvalidData(String name, String type, String address) {
        Store store = new Store().setName(name).setType(type).setAddress(address);
        Assert.assertFalse(add(user.get(Lib.Privileges.ADMIN).getUsername(), store));
    }

    @Test(dependsOnMethods = {"addValidData", "addDuplicateData", "addInvalidData"})
    public void all() {
        long userStoreCount = 0;
        for (User user : userRepository.findAll()) {
            userStoreCount += storeServices.all(user.getUsername()).getBody().size();
        }
        Assert.assertEquals(userStoreCount, storeRepository.count());
    }

    public boolean addProduct(long storeId, ProductStack pStack, String username) {
        return storeServices.addProduct(storeId, pStack, username).getBody();
    }



}