package unit;

import jonamatoka.violet.App;
import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.*;
import jonamatoka.violet.data.repo.*;
import jonamatoka.violet.web.services.*;
import net.openhft.hashing.LongHashFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*TODO/Mourad: Disseminate this fat class*/

@SpringBootTest(classes = App.class)
public class TestVioletServices extends AbstractTestNGSpringContextTests {

    @Autowired protected BrandRepository brandRepository;
    @Autowired protected CategoryRepository categoryRepository;
    @Autowired protected ProductRepository productRepository;
    @Autowired protected StoreRepository storeRepository;
    @Autowired protected UserRepository userRepository;
    @Autowired protected BrandServices brandServices;
    @Autowired protected CategoryServices categoryServices;
    @Autowired protected ProductServices productServices;
    @Autowired protected StoreServices storeServices;
    @Autowired protected UserServices userServices;

    protected Map<Integer, User> user = new HashMap<>();

    @BeforeClass
    public void setup() {
        User testAdmin = new User()
                .setUsername("TestAdmin")
                .setEmail("TestAdmin@violet.com")
                .setHash(LongHashFunction.xx().hashChars("TestAdmin")).setPriviliges(Lib.Privileges.ADMIN);

        user.put(Lib.Privileges.ADMIN, testAdmin);
        userRepository.save(testAdmin);

        User testOwner = new User()
                .setUsername("TestOwner")
                .setEmail("TestOwner@violet.com")
                .setHash(LongHashFunction.xx().hashChars("TestOwner")).setPriviliges(Lib.Privileges.OWNER);

        user.put(Lib.Privileges.OWNER, testOwner);
        userRepository.save(testOwner);

        User testUser = new User()
                .setUsername("TestUser")
                .setEmail("TestUser@violet.com")
                .setHash(LongHashFunction.xx().hashChars("TestUser")).setPriviliges(Lib.Privileges.USER);

        user.put(Lib.Privileges.USER, testUser);
        userRepository.save(testUser);
    }

    protected boolean register(String username, String email, String pass) {
        return userServices.register(username, email, pass).getBody();
    }

    protected boolean addBrand(Brand brand) {
        return brandServices.add(brand).getBody();
    }

    protected boolean addCategory(Category category) {
        return categoryServices.add(category).getBody();
    }

    protected boolean addProduct(Product product) {
        return productServices.add(product).getBody();
    }

    protected boolean addStore(String username, Store store) {
        return storeServices.add(store, username).getBody();
    }

    protected List<Brand> allBrands() {
        return brandServices.all().getBody();
    }

}
