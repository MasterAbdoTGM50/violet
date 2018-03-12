package jonamatoka.violet;

import jonamatoka.violet.account.User;
import jonamatoka.violet.product.Brand;
import jonamatoka.violet.product.Category;
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

    }

}
