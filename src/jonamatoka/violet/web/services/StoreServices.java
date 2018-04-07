package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.account.User;
import jonamatoka.violet.data.repo.StoreRepository;
import jonamatoka.violet.data.repo.UserRepository;
import jonamatoka.violet.store.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StoreServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @RequestMapping(value = Lib.Mappings.STORE_SERVICES, method = RequestMethod.GET)
    public ResponseEntity<List<Store>> getAllStores() {

        List<Store> stores = new ArrayList<>();
        storeRepository.findAll().forEach(stores::add);

        return new ResponseEntity<>(stores, HttpStatus.OK);

    }

    @RequestMapping(value = Lib.Mappings.GET_STORE_SYSTEM, method = RequestMethod.GET)
    public ResponseEntity<Store> getStore(@PathVariable("storeId") long storeId) {

        Store store = storeRepository.findOne(storeId);

        return new ResponseEntity<>(store, HttpStatus.OK);

    }

    @RequestMapping(value = Lib.Mappings.ADD_STORE_SYSTEM, method = RequestMethod.POST)
    public ResponseEntity<Boolean> addStoreToSystem(@RequestBody Store store) {

        User user = userRepository.findOne((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        store.setOwnerId(user.getUsername());

        storeRepository.save(store);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }
}