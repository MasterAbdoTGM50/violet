package unit;

import jonamatoka.violet.App;
import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.model.User;
import jonamatoka.violet.data.repo.StoreRepository;

import jonamatoka.violet.data.repo.UserRepository;
import jonamatoka.violet.web.services.StoreServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.Assert;
import org.testng.annotations.*;

@SpringBootTest(classes = App.class)
public class TestStoreServices extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreServices storeServices;

    private boolean add(Store store, String username) {
        return storeServices.add(store, username).getBody();
    }

    @DataProvider(name = "storeValidDataProvider")
    public Object[][] storeValidDataProvider() {
        return new Object[][] {
                {"Abdo", "Store_1", "Onsite", "Some St."}
        };
    }

    @Test(dataProvider = "storeValidDataProvider")
    public void addValidData(String username, String name, String type, String address) {
        Store store = new Store()
                .setName(name)
                .setType(type)
                .setAddress(address);

        Assert.assertTrue(add(store, username));
    }

    @Test(dataProvider = "storeValidDataProvider", dependsOnMethods = "addValidData")
    public void addDuplicateData(String username, String name, String type, String address) {
        Store store = new Store()
                .setName(name)
                .setType(type)
                .setAddress(address);

        Assert.assertTrue(add(store, username));
    }

    @DataProvider(name = "storeInvalidDataProvider")
    public Object[][] storeInvalidDataProvider() {
        return new Object[][] {
                {" ", "Store_1", "Onsite", "Some St."},
                {"Abdo", " ", "Onsite", "Some St."},
                {"Abdo", "Store_1", " ", "Some St."},
                {"Abdo", "Store_1", "Onsite", "  "},
        };
    }

    @Test(dataProvider = "storeInvalidDataProvider")
    public void addInvalidData(String username, String name, String type, String address) {
        Store store = new Store()
                .setName(name)
                .setType(type)
                .setAddress(address);

        Assert.assertFalse(add(store, username));
    }

    @Test(dependsOnMethods = {"addValidData", "addDuplicateData", "addInvalidData"})
    public void all() {
        long userStoreCount = 0;
        for (User user : userRepository.findAll()) {
            userStoreCount += storeServices.all(user.getUsername()).getBody().size();
        }
        Assert.assertEquals(userStoreCount, storeRepository.count());
    }

}