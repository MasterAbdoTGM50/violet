package jonamatoka.violet.util.qstat.collection;

import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.data.model.ProductStack;
import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.data.repo.StoreRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductsByStoreId implements Collection<Product> {

    private long storeId;
    private StoreRepository storeRepository=null;
    private ProductRepository productRepository=null;

    public ProductsByStoreId(StoreRepository storeRepository, ProductRepository productRepository, long storeId){

        this.storeId = storeId;
        this.storeRepository=storeRepository;
        this.productRepository=productRepository;

    }

    @Override
    public List<Product> calc() {

        List products = new ArrayList<Product>();
        Store s=storeRepository.findOne(storeId);
        for(ProductStack ps : s.getInventory().get()){
            products.add(productRepository.findOne(ps.getProductId()));
        }

        return products;

    }

}
