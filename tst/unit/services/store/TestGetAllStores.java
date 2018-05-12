package unit.services.store;

import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.model.User;

import jonamatoka.violet.data.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestGetAllStores extends TestStoreService {

    @Autowired
    private UserRepository userRepository;

    private List<Store> all(String ownerId) {
        return getStoreServices().all(ownerId).getBody();
    }

    @Test
    public void all() {
        long userStoreCount = 0;
        for (User user : userRepository.findAll()) {
            userStoreCount += all(user.getUsername()).size();
        }
        Assert.assertEquals(userStoreCount, getStoreRepository().count());
    }

}