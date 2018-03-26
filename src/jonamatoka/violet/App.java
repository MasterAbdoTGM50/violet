package jonamatoka.violet;

import jonamatoka.violet.util.MockBed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    @Autowired
    static MockBed mock;

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
        mock.mockmock();

    }

}
