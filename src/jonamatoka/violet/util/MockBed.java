package jonamatoka.violet.util;

import jonamatoka.violet.account.User;
import jonamatoka.violet.data.repo.*;
import jonamatoka.violet.product.Brand;
import jonamatoka.violet.product.Category;
import jonamatoka.violet.product.Product;
import jonamatoka.violet.product.ProductStack;
import jonamatoka.violet.store.Store;
import net.openhft.hashing.LongHashFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MockBed implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired private UserRepository userRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private BrandRepository brandRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private StoreRepository storeRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        User temsah = new User().setUsername("Abdo")
                        .setEmail("masterabdotgm50@ymail.com")
                        .setHash(LongHashFunction.xx().hashChars("Temsah"))
                        .setPriviliges(0);

        User mourad = new User().setUsername("Mourad")
                        .setEmail("sherieymourad1997@gmail.com")
                        .setHash(LongHashFunction.xx().hashChars("Sheriey"))
                        .setPriviliges(3);

        User jasmin = new User().setUsername("Jasmin")
                        .setEmail("jasminsmail@somedomain.com")
                        .setHash(LongHashFunction.xx().hashChars("Shehab"))
                        .setPriviliges(6);

        temsah = userRepository.save(temsah);
        mourad = userRepository.save(mourad);
        jasmin = userRepository.save(jasmin);

        Brand hp = new Brand().setName("HP");
        Brand lenovo = new Brand().setName("LENOVO");
        Brand marmator = new Brand().setName("Marmator");

        Category laptop = new Category().setName("Laptop");
        Category phone = new Category().setName("Phone");
        Category cats = new Category().setName("CATS");

        hp = brandRepository.save(hp);
        lenovo = brandRepository.save(lenovo);
        marmator = brandRepository.save(marmator);

        laptop = categoryRepository.save(laptop);
        phone = categoryRepository.save(phone);
        cats = categoryRepository.save(cats);

        Store mockStore = new Store();
        mockStore.setOwnerId("Mourad");
        mockStore.setName("Marmatos");
        mockStore.setAddress("Some addr.");
        mockStore.setType("Onsite");

        mockStore = storeRepository.save(mockStore);

        Product mockProduct = new Product();
        mockProduct.setName("Marmatos Pizza");
        mockProduct.setBrand(marmator);
        mockProduct.setCategory(cats);
        mockProduct = productRepository.save(mockProduct);

        ProductStack mockProductStack = new ProductStack()
                .setProductId(mockProduct.getId())
                .setProductName(mockProduct.getName())
                .setPrice(10)
                .setQuantity(1);

        mockStore.getInventory().add(mockProductStack);

        storeRepository.save(mockStore);

    }

}
