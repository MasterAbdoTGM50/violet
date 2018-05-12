package jonamatoka.violet.util.Statisticsnewversion.StatQuery;

import jonamatoka.violet.data.repo.ProductRepository;
import jonamatoka.violet.data.repo.StoreRepository;
import jonamatoka.violet.util.Statisticsnewversion.Collection.*;
import org.springframework.beans.factory.annotation.Autowired;

public class CollectionFactory {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;

    public final Collection getIStatisticQuery(String query, String id) {
        if (id.isEmpty()) {
            switch (query) {
                case "Products":
                    return new Products(productRepository);

                case "Stores":
                    return new Stores(storeRepository);
            }
        } else {
            switch (query) {
                case "ProductsById":
                    return new ProductsById(productRepository, Long.parseLong(id));

                case "ProductsByOwnerId":
                    return new ProductsByOwnerId(storeRepository, productRepository, id);

                case "ProductsByStoreId":
                    return new ProductsByStoreId(storeRepository, productRepository, Long.parseLong(id));

                case "StoresById":
                    return new StoresById(storeRepository, Long.parseLong(id));

                case "StoresByOwnerId":
                    return new StoresByOwnerId(storeRepository, id);
            }
        }
        return null;
    }

}
