package unit.services.store;

import jonamatoka.violet.data.repo.StoreRepository;
import jonamatoka.violet.web.services.StoreServices;

import org.springframework.beans.factory.annotation.Autowired;

import unit.TestVioletService;

public class TestStoreService extends TestVioletService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreServices storeServices;

    public StoreRepository getStoreRepository() { return storeRepository; }

    public StoreServices getStoreServices() { return storeServices; }

}
