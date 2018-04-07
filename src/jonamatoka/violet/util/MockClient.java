package jonamatoka.violet.util;

import jonamatoka.violet.Lib;
import jonamatoka.violet.account.User;
import jonamatoka.violet.product.Brand;
import jonamatoka.violet.product.Category;
import jonamatoka.violet.product.Product;
import jonamatoka.violet.store.Store;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class MockClient implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        MockClient mc = new MockClient();

    }

    private RestTemplate rest;
    private String SERVICE_ROOT = "http://localhost:8585";
    private String username;
    private String password;

    public MockClient() { rest = new RestTemplate(); }

    private HttpHeaders getHeaders() { return new HttpHeaders(); }

    private HttpHeaders getSecureHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + new String(Base64.getEncoder().encode((username + ":" + password).getBytes())));

        return headers;
    }

    public void login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ResponseEntity<Boolean> register(User user, String pass) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", user.getUsername());
        map.add("email", user.getEmail());
        map.add("pass", pass);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, getHeaders());

        return rest.postForEntity(SERVICE_ROOT + Lib.Mappings.REGISTER, request, Boolean.class);
    }

    public ResponseEntity<String> getAllBrands() {

        return rest.getForEntity(SERVICE_ROOT + Lib.Mappings.BRAND_SERVICES, String.class);

    }

    public ResponseEntity<Boolean> addBrand(Brand brand) {

        HttpEntity<Brand> request = new HttpEntity<>(brand, getSecureHeaders());

        return rest.postForEntity(SERVICE_ROOT + Lib.Mappings.ADD_BRAND_SYSTEM, request, Boolean.class);
    }

    public ResponseEntity<String> getAllCategory() {

        return rest.getForEntity(SERVICE_ROOT + Lib.Mappings.CATEGORY_SERVICES, String.class);

    }

    public ResponseEntity<Boolean> addCategory(Category category) {

        HttpEntity<Category> request = new HttpEntity<>(category, getSecureHeaders());

        return rest.postForEntity(SERVICE_ROOT + Lib.Mappings.ADD_CATEGORY_SYSTEM, request, Boolean.class);
    }

    public ResponseEntity<String> getAllProducts() {

        return rest.getForEntity(SERVICE_ROOT + Lib.Mappings.PRODUCT_SERVICES, String.class);

    }

    public ResponseEntity<Boolean> addProduct(Product product) {

        HttpEntity<Product> request = new HttpEntity<>(product, getSecureHeaders());

        return rest.postForEntity(SERVICE_ROOT + Lib.Mappings.ADD_CATEGORY_SYSTEM, request, Boolean.class);
    }

    public ResponseEntity<String> getAllStores() {

        return rest.getForEntity(SERVICE_ROOT + Lib.Mappings.STORE_SERVICES, String.class);

    }

    public ResponseEntity<String> getStore(long storeId) {

        return rest.getForEntity(SERVICE_ROOT + Lib.Mappings.STORE_SERVICES + "/" + storeId, String.class);

    }

    public ResponseEntity<Boolean> addStore(Store store) {

        HttpEntity<Store> request = new HttpEntity<>(store, getSecureHeaders());

        return rest.postForEntity(SERVICE_ROOT + Lib.Mappings.ADD_STORE_SYSTEM, request, Boolean.class);
    }

    public ResponseEntity<Boolean> addProductToStore(long storeId, long productId) {

        HttpEntity request = new HttpEntity(getSecureHeaders());

        return rest.postForEntity(SERVICE_ROOT + Lib.Mappings.STORE_SERVICES + "/" + storeId + "/" + productId, request, Boolean.class);
    }

}

