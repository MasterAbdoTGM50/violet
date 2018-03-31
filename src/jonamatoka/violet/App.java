package jonamatoka.violet;

import jonamatoka.violet.util.stat.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class App {

    public static void main(String[] args) throws IOException {
        //left for testing
/*
       String jsonString = "{\"products\":[{\"name\" :\"p1\",\"price\":1.2},{\"name\" :\"p2\",\"price\":3}]}";

        Metrics metric= new Metrics(jsonString);
        System.out.println(metric.getMetics("products/price",new MAX(),"name"));
*/
        //SpringApplication.run(App.class, args);

    }

}
