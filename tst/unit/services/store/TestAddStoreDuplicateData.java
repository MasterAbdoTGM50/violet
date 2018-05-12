package unit.services.store;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.Store;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestAddStoreDuplicateData extends TestStoreService {

    @DataProvider(name = "storeDuplicateDataProvider")
    public Object[][] storeDuplicateDataProvider() {
        return new Object[][] {
                {"Store_1", "Onsite", "Some St."}
        };
    }

    @Test(dataProvider = "storeDuplicateDataProvider")
    public void addStoreDuplicateDataAsOwner(String name, String type, String address) {
        Store store = new Store().setName(name).setType(type).setAddress(address);
        getStoreServices().add(store, getUser(Lib.Privileges.OWNER).getUsername());
        Assert.assertFalse(getStoreServices().add(store, getUser(Lib.Privileges.OWNER).getUsername()).getBody());
    }

}
