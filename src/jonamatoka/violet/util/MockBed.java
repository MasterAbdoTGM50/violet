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

public class MockBed {

    @Autowired private UserRepository userRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private BrandRepository brandRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private StoreRepository storeRepository;

    public void mockmock() {

        userRepository.save(
                new User().setUsername("Abdo")
                        .setEmail("masterabdotgm50@ymail.com")
                        .setHash(LongHashFunction.xx().hashChars("Temsah"))
                        .setPriviliges(0)
        );

        userRepository.save(
                new User().setUsername("Mourad")
                        .setEmail("sherieymourad1997@gmail.com")
                        .setHash(LongHashFunction.xx().hashChars("Sheriey"))
                        .setPriviliges(3)
        );

        userRepository.save(
                new User().setUsername("Jasmin")
                        .setEmail("jasminsmail@somedomain.com")
                        .setHash(LongHashFunction.xx().hashChars("Shehab"))
                        .setPriviliges(6)
        );

        brandRepository.save(new Brand().setName("HP"));

        brandRepository.save(new Brand().setName("LENOVO"));

        categoryRepository.save(new Category().setName("Laptop"));

        categoryRepository.save(new Category().setName("Phone"));

        Store mockStore = new Store();
        mockStore.setOwnerId("FCIWorks");
        mockStore.setName("Marmatos");
        mockStore.setAddress("Some addr.");
        mockStore.setType("Onsite");

        mockStore = storeRepository.save(mockStore);

        Product mockProduct = new Product();
        mockProduct.setName("Marmatos Pizza");
        mockProduct.setBrand(new Brand().setName("Marmatos"));
        mockProduct.setCategory(new Category().setName("CATS"));
        productRepository.save(mockProduct);

        ProductStack mockProductStack = new ProductStack()
                .setProductId(mockProduct.getId())
                .setProductName(mockProduct.getName())
                .setPrice(10)
                .setQuantity(1);

        mockStore.getInventory().add(mockProductStack);

        storeRepository.save(mockStore);

    }

}
