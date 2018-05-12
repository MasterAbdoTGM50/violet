package unit.services.store;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.Store;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestAddStoreDuplicateData extends TestVioletServices {

    @DataProvider(name = "storeDuplicateDataProvider")
    public Object[][] storeDuplicateDataProvider() {
        return new Object[][] {
                {"Store_1", "Onsite", "Some St."}
        };
    }

    @Test(dataProvider = "storeDuplicateDataProvider")
    public void addStoreDuplicateDataAsOwner(String name, String type, String address) {
        Store store = new Store().setName(name).setType(type).setAddress(address);
        addStore(user.get(Lib.Privileges.OWNER).getUsername(), store);
        Assert.assertFalse(addStore(user.get(Lib.Privileges.OWNER).getUsername(), store));
    }

}
