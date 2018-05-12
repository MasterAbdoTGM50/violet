package unit.services.store;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.Store;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAddStoreValidData extends TestStoreService {

    @DataProvider(name = "storeValidDataProvider")
    public Object[][] storeValidDataProvider() {
        return new Object[][] {
                {"Store_1", "Onsite", "Some St."}
        };
    }

    @Test(dataProvider = "storeValidDataProvider")
    public void addStoreValidDataAsOwner(String name, String type, String address) {
        Store store = new Store().setName(name).setType(type).setAddress(address);
        Assert.assertTrue(getStoreServices().add(store, getUser(Lib.Privileges.OWNER).getUsername()).getBody());
    }

}
