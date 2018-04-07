package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.product.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = Lib.Mappings.PRODUCT_SERVICES, method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);

        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @RequestMapping(value = Lib.Mappings.ADD_PRODUCT_SYSTEM, method = RequestMethod.POST)
    public ResponseEntity<Boolean> addProductToSystem(@RequestBody Product product) {

        productRepository.save(product);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}
