package unit.services.store;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.Store;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unit.TestVioletServices;

public class TestAddStoreInvalidData extends TestVioletServices {

    @DataProvider(name = "storeInvalidDataProvider")
    public Object[][] storeInvalidDataProvider() {
        return new Object[][] {
                {"Store_1", "Onsite", "Some St."},
                {" ", "Onsite", "Some St."},
                {"!~", " ", "Some St."},
                {"Stre_1", "Onsite", "  "}
        };
    }

    @Test(dataProvider = "storeInvalidDataProvider")
    public void addStoreInvalidDataAsOwner(String name, String type, String address) {
        Store store = new Store().setName(name).setType(type).setAddress(address);
        Assert.assertFalse(addStore(user.get(Lib.Privileges.OWNER).getUsername(), store));
    }

}
