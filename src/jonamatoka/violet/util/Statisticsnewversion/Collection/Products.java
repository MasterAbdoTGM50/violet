package jonamatoka.violet.util.Statisticsnewversion.Collection;

import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.data.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class Products implements Collection<Product> {

    private ProductRepository productRepository = null;

    public Products(ProductRepository productRepository){

        this.productRepository = productRepository;

    }

    @Override
    public List<Product> calc() {

        List products = new ArrayList<Product>();
        productRepository.findAll().forEach(products::add);

        return products;

    }

}
