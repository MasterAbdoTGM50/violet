package unit.services.product;

import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.web.services.ProductServices;

import org.springframework.beans.factory.annotation.Autowired;

import unit.AbstractTestVioletService;

public abstract class AbstractTestProductService extends AbstractTestVioletService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServices productServices;

    public ProductRepository getProductRepository() { return productRepository; }

    public ProductServices getProductServices() { return productServices; }

}
