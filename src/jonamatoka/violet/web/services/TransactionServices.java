package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.*;
import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.data.repo.StoreRepository;
import jonamatoka.violet.data.repo.TransactionRepository;
import jonamatoka.violet.data.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(Lib.Mappings.API_V1_TRANSACTION)
public class TransactionServices {

    private static List<DiscountRule> discountRules = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{storeId}/{stackId}/{quantity}")
    public ResponseEntity<?> getTransactionDetails(@PathVariable("storeId") long storeId,
                                                   @PathVariable("stackId") long stackId,
                                                   @PathVariable("quantity") int quantity,
                                                   @AuthenticationPrincipal String username) {

        User user = userRepository.findOne(username);
        Store store = storeRepository.findOne(storeId);
        ProductStack stack = store.getInventory().findById(stackId);

        double discount = discountRules.stream()
                .filter(r -> r.satisfied(user, store, stack, quantity))
                .mapToDouble(DiscountRule::discount).sum();

        double price = (stack.getPrice() * quantity) * (1 - discount);
        price = (price < 0) ? 0 : price;

        if(stack.getQuantity() < quantity) { return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE); }
        if(!user.getWallet().check(price)) { return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE); }

        Transaction transaction = transactionRepository.save(
                new Transaction()
                        .setBuyerId(username)
                        .setStoreId(storeId)
                        .setStackId(stackId)
                        .setQuantity(quantity)
                        .setPrice(price)
        );

        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }

    @PostMapping("/{transactionId}")
    public ResponseEntity<?> completeTransaction(@PathVariable("transactionId") long transactionId) {

        Transaction transaction = transactionRepository.findOne(transactionId);

        if(transaction.getState() == Transaction.State.COMPLETE) {
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }

        User user = userRepository.findOne(transaction.getBuyerId());
        Store store = storeRepository.findOne(transaction.getStoreId());
        ProductStack stack = store.getInventory().findById(transaction.getStackId());
        Product product = productRepository.findOne(stack.getProductId());

        user.getWallet().deduct(transaction.getPrice());
        store.order(transaction.getQuantity());
        stack.order(transaction.getQuantity());
        product.order(transaction.getQuantity());

        userRepository.save(user);
        storeRepository.save(store);
        productRepository.save(product);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    /*TODO//Temsah: Do it in a way that doesn't suck*/
    static {

        discountRules.add(new DiscountRule() {
            @Override
            public boolean satisfied(User user, Store store, ProductStack stack, int quantity) {
                return user.getPriviliges() == Lib.Privileges.OWNER;
            }

            @Override
            public double discount() {
                return .15;
            }
        });

        discountRules.add(new DiscountRule() {
            @Override
            public boolean satisfied(User user, Store store, ProductStack stack, int quantity) {
                return quantity >= 2;
            }

            @Override
            public double discount() {
                return .10;
            }
        });

        discountRules.add(new DiscountRule() {
            @Override
            public boolean satisfied(User user, Store store, ProductStack stack, int quantity) {
                return stack.orders() == 0;
            }

            @Override
            public double discount() {
                return .05;
            }
        });

    }

    private static abstract class DiscountRule {

        public abstract boolean satisfied(User user, Store store, ProductStack stack, int quantity);

        public abstract double discount();

    }

}
