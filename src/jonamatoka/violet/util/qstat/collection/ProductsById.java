package jonamatoka.violet.util.qstat.collection;

import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.data.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductsById implements Collection<Product> {

    private long productId;
    private ProductRepository productRepository = null;

    public ProductsById(ProductRepository productRepository, long productId){

        this.productId = productId;
        this.productRepository = productRepository;

    }

    @Override
    public List<Product> calc() {

        List products = new ArrayList<Product>();
        products.add(productRepository.findOne(productId));

        return products;

    }

}
