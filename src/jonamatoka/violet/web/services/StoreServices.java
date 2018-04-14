package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.ProductStack;
import jonamatoka.violet.data.model.User;
import jonamatoka.violet.data.repo.StoreRepository;
import jonamatoka.violet.data.repo.UserRepository;
import jonamatoka.violet.data.model.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(Lib.Mappings.API_V1_STORE)
public class StoreServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping
    public ResponseEntity<?> all(@RequestParam(name = "ownerId", required = false) String ownerId) {

        List<Store> stores = new ArrayList<>();

        if(null == ownerId) { storeRepository.findAll().forEach(stores::add); }
        else {
            storeRepository.findAll().forEach(s -> { if(ownerId.equals(s.getOwnerId())) { stores.add(s); } });
        }

        return new ResponseEntity<>(stores, HttpStatus.OK);

    }

    @GetMapping("/{storeId}")
    public ResponseEntity<?> get(@PathVariable("storeId") long storeId) {

        Store store = storeRepository.findOne(storeId);

        return new ResponseEntity<>(store, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Store store) {

        User user = userRepository.findOne((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        store.setOwnerId(user.getUsername());

        storeRepository.save(store);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @PostMapping("/{storeId}")
    public ResponseEntity<?> add(@PathVariable("storeId") long storeId, @RequestBody ProductStack pStack) {

        Store store = storeRepository.findOne(storeId);
        store.getInventory().add(pStack);
        storeRepository.save(store);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}