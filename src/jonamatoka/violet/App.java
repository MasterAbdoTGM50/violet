package jonamatoka.violet;

import jonamatoka.violet.account.User;
import jonamatoka.violet.util.NitriteHelper;
import net.openhft.hashing.LongHashFunction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        NitriteHelper.insert(
                new User().setUsername("Abdo")
                .setEmail("masterabdotgm50@ymail.com")
                .setHash(LongHashFunction.xx().hashChars("Temsah"))
                .setPriviliges(0)
                , User.class
        );

        SpringApplication.run(App.class, args);

    }

}