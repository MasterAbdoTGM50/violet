package jonamatoka.violet.util.Statisticsnewversion;

import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.repo.StoreRepository;


import java.util.ArrayList;
import java.util.List;

public class Stores implements Collection<Store> {

    private StoreRepository storeRepository = null;

    Stores(StoreRepository storeRepository){

        this.storeRepository = storeRepository;

    }

    @Override
    public List<Store> calc() {

        List stores = new ArrayList<Store>();
        storeRepository.findAll().forEach(stores::add);

        return stores;

    }

}
