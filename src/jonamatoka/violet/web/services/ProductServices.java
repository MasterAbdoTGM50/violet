package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.data.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(Lib.Mappings.API_V1_PRODUCT)
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository) {

        this.productRepository = productRepository;

    }

    @GetMapping
    public ResponseEntity<?> all() {

        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);

        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> get(@PathVariable("productId") long productId) {

        Product store = productRepository.findOne(productId);

        return new ResponseEntity<>(store, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Product product) {

        productRepository.save(product);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}
