package jonamatoka.violet.util.Statisticsnewversion.Collection;

import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.data.model.ProductStack;
import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.data.repo.StoreRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductsByOwnerId implements Collection<Product> {

    private String ownerId;
    private StoreRepository storeRepository=null;
    private ProductRepository productRepository=null;

    public ProductsByOwnerId(StoreRepository storeRepository, ProductRepository productRepository, String ownerId){

        this.ownerId=ownerId;
        this.storeRepository=storeRepository;
        this.productRepository=productRepository;

    }

    @Override
    public List<Product> calc() {

        List products = new ArrayList<Product>();
        List <Store> storesOwned = new ArrayList<>(new StoresByOwnerId(storeRepository,ownerId).calc());
        for(Store s:storesOwned) {
            for (ProductStack ps : s.getInventory().get()) {
                products.add(productRepository.findOne(ps.getProductId()));
            }
        }
        return products;
    }

}

