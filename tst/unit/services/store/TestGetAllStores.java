package unit.services.store;

import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.model.User;

import org.testng.Assert;
import org.testng.annotations.Test;

import unit.TestVioletServices;

import java.util.List;

public class TestGetAllStores extends TestVioletServices {

    private List<Store> all(String ownerId) {
        return storeServices.all(ownerId).getBody();
    }

    @Test
    public void all() {
        long userStoreCount = 0;
        for (User user : userRepository.findAll()) {
            userStoreCount += all(user.getUsername()).size();
        }
        Assert.assertEquals(userStoreCount, storeRepository.count());
    }

}