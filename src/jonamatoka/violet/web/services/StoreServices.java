package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.Offer;
import jonamatoka.violet.data.model.ProductStack;
import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.model.User;
import jonamatoka.violet.data.repo.StoreRepository;
import jonamatoka.violet.data.repo.action.StoreActionRepository;
import jonamatoka.violet.util.action.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*TODO//Temsah: Refactor Store Services as the class is very fat*/

@RestController
@CrossOrigin
@RequestMapping(Lib.Mappings.API_V1_STORE)
public class StoreServices {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreActionRepository storeActionRepository;

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
    public ResponseEntity<?> add(@RequestBody Store store,
                                 @AuthenticationPrincipal String username) {

        store.setOwnerId(username);
        storeRepository.save(store);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @GetMapping("/{storeId}/products")
    public ResponseEntity<?> allProducts(@PathVariable("storeId") long storeId) {

        Store store = storeRepository.findOne(storeId);

        return new ResponseEntity<>(store.getInventory().get(), HttpStatus.OK);

    }

    @PostMapping("/{storeId}/products")
    public ResponseEntity<?> addProduct(@PathVariable("storeId") long storeId,
                                        @RequestBody ProductStack pStack,
                                        @AuthenticationPrincipal String username) {

        Store store = storeRepository.findOne(storeId);

        if(!(store.getOwnerId().equals(username) || store.getCollaborators().contains(username))) {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        StoreAddProductAction action = new StoreAddProductAction().setStack(pStack);
        action.exec(store);

        store.getActions().add(action);

        storeActionRepository.save(action);
        storeRepository.save(store);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @DeleteMapping("/{storeId}/products/{stackId}")
    public ResponseEntity<?> removeProduct(@PathVariable("storeId") long storeId,
                                           @PathVariable long stackId,
                                           @AuthenticationPrincipal String username) {

        Store store = storeRepository.findOne(storeId);

        if(!(store.getOwnerId().equals(username) || store.getCollaborators().contains(username))) {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        StoreRemProductAction action = new StoreRemProductAction().setStack(new ProductStack().setStackId(stackId));
        action.exec(store);

        store.getActions().add(action);

        storeActionRepository.save(action);
        storeRepository.save(store);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @PostMapping("/{storeId}/collaborators")
    public ResponseEntity<?> addCollaborator(@PathVariable("storeId") long storeId,
                                             @RequestBody User collaborator,
                                             @AuthenticationPrincipal String username) {

        Store store = storeRepository.findOne(storeId);

        if(!store.getOwnerId().equals(username)) { return new ResponseEntity<>(false, HttpStatus.FORBIDDEN); }

        store.getCollaborators().add(collaborator.getUsername());
        storeRepository.save(store);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @PostMapping("/{storeId}/offers")
    public ResponseEntity<?> addOffer(@PathVariable("storeId") long storeId,
                                      @RequestBody Offer offer,
                                      @AuthenticationPrincipal String username) {

        Store store = storeRepository.findOne(storeId);
        if(!(store.getOwnerId().equals(username) || store.getCollaborators().contains(username))) {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        StoreAddOfferAction action = new StoreAddOfferAction().setOffer(offer);
        action.exec(store);

        store.getActions().add(action);

        storeActionRepository.save(action);
        storeRepository.save(store);

        return new ResponseEntity<>(true, HttpStatus.FORBIDDEN);

    }

    @DeleteMapping("/{storeId}/offers")
    public ResponseEntity<?> removeOffer(@PathVariable("storeId") long storeId,
                                         @RequestBody Offer offer,
                                         @AuthenticationPrincipal String username) {

        Store store = storeRepository.findOne(storeId);
        if(!(store.getOwnerId().equals(username) || store.getCollaborators().contains(username))) {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        StoreRemOfferAction action = new StoreRemOfferAction().setOffer(offer);
        action.exec(store);

        store.getActions().add(action);

        storeActionRepository.save(action);
        storeRepository.save(store);

        return new ResponseEntity<>(true, HttpStatus.FORBIDDEN);

    }

    @GetMapping("/{storeId}/actions")
    public ResponseEntity<?> actions(@PathVariable("storeId") long storeId,
                                     @AuthenticationPrincipal String username) {

        Store store = storeRepository.findOne(storeId);
        if(!store.getOwnerId().equals(username)) { return new ResponseEntity<>(false, HttpStatus.FORBIDDEN); }

        return new ResponseEntity<>(store.getActions(), HttpStatus.OK);

    }

    @PostMapping("/{storeId}/actions/{actionId}/{state}")
    public ResponseEntity<?> doAction(@PathVariable("storeId") long storeId,
                                      @PathVariable("actionId") long actionId,
                                      @PathVariable("state") String state,
                                      @AuthenticationPrincipal String username) {

        Store store = storeRepository.findOne(storeId);
        if(!store.getOwnerId().equals(username)) { return new ResponseEntity<>(false, HttpStatus.FORBIDDEN); }

        StoreAction action = store.getActions().stream().filter(a -> a.getId() == actionId).findFirst().orElse(null);

        if(action != null) {

            if(state.equalsIgnoreCase("do")) { action.exec(store); }
            if(state.equalsIgnoreCase("undo")) { action.unexec(store); }

            storeActionRepository.save(action);
            storeRepository.save(store);

        }

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}