package jonamatoka.violet.util.Statisticsnewversion.Collection;

import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.data.repo.StoreRepository;
import jonamatoka.violet.util.Statisticsnewversion.Collection.Collection;

import java.util.ArrayList;
import java.util.List;

public class StoresByOwnerId implements Collection<Store> {

    private String ownerId;
    private StoreRepository storeRepository = null;

    public StoresByOwnerId(StoreRepository storeRepository, String ownerId){

        this.ownerId = ownerId;
        this.storeRepository = storeRepository;

    }

    @Override
    public List<Store> calc() {

        List stores = new ArrayList<Store>();
        storeRepository.findAll().forEach(s -> { if(ownerId.equals(s.getOwnerId())) { stores.add(s); } });

        return stores;

    }

}