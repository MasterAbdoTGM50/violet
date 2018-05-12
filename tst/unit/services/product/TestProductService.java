package unit.services.product;

import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.web.services.ProductServices;

import org.springframework.beans.factory.annotation.Autowired;

import unit.TestVioletService;

public class TestProductService extends TestVioletService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServices productServices;

    public ProductRepository getProductRepository() { return productRepository; }

    public ProductServices getProductServices() { return productServices; }

}
