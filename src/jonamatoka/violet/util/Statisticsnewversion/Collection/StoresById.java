package jonamatoka.violet.util.Statisticsnewversion.Collection;

import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.repo.StoreRepository;
import jonamatoka.violet.util.Statisticsnewversion.Collection.Collection;

import java.util.ArrayList;
import java.util.List;

public class StoresById implements Collection<Store> {

    private long storeId;
    private StoreRepository storeRepository = null;

    public StoresById(StoreRepository storeRepository, long storeId){

        this.storeId = storeId;
        this.storeRepository = storeRepository;

    }

    @Override
    public List<Store> calc() {

        List stores = new ArrayList<Store>();
        stores.add(storeRepository.findOne(storeId));

        return stores;

    }

}