package jonamatoka.violet;

import jonamatoka.violet.account.User;
import jonamatoka.violet.product.*;
import jonamatoka.violet.store.Store;
import jonamatoka.violet.store.Stores;
import jonamatoka.violet.util.NitriteHelper;

import net.openhft.hashing.LongHashFunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        mock();
        SpringApplication.run(App.class, args);

    }

    private static void mock() {

        NitriteHelper.get().insert(
                new User().setUsername("Abdo")
                        .setEmail("masterabdotgm50@ymail.com")
                        .setHash(LongHashFunction.xx().hashChars("Temsah"))
                        .setPriviliges(0)
                , User.class
        );

        NitriteHelper.get().insert(
                new User().setUsername("Mourad")
                        .setEmail("sherieymourad1997@gmail.com")
                        .setHash(LongHashFunction.xx().hashChars("Sheriey"))
                        .setPriviliges(3)
                , User.class
        );

        NitriteHelper.get().insert(
                new User().setUsername("Jasmin")
                        .setEmail("jasminsmail@somedomain.com")
                        .setHash(LongHashFunction.xx().hashChars("Shehab"))
                        .setPriviliges(6)
                , User.class
        );

        NitriteHelper.get().insert(
                new Brand().setName("HP")
                , Brand.class
        );

        NitriteHelper.get().insert(
                new Brand().setName("LENOVO")
                , Brand.class
        );

        NitriteHelper.get().insert(
                new Category().setName("Laptop")
                , Category.class
        );

        NitriteHelper.get().insert(
                new Category().setName("Phone")
                , Category.class
        );

        Store mockStore = new Store();
        mockStore.setId("1");
        mockStore.setOwnerId("FCIWorks");
        mockStore.setName("Marmatos");
        mockStore.setAddress("Some addr.");
        mockStore.setType("Onsite");

        Stores.get().add(mockStore);

        Product mockProduct = new Product();
        mockProduct.setId("1");
        mockProduct.setName("Marmatos Pizza");
        mockProduct.setBrand(new Brand().setName("Marmatos"));
        mockProduct.setCategory(new Category().setName("CATS"));
        Products.get().add(mockProduct);

        ProductStack mockProductStack = new ProductStack()
                                                            .setProductId(mockProduct.getId())
                                                            .setProductName(mockProduct.getName())
                                                            .setPrice(10)
                                                            .setQuantity(1);

        mockStore.getInventory().add(mockProductStack);

        Stores.get().update(mockStore);

    }

}
