package jonamatoka.violet.util.Statisticsnewversion;

import jonamatoka.violet.data.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreOrders extends IStatistic {

    StoreOrders (Collection<Store> dc){
        super(dc);
    }

    @Override
    public ArrayList<Double> calc(){

        ArrayList<Double> res = new ArrayList<>();
        List<Store> stores = dataCollection.calc();
        for(Store s: stores)
            res.add((double)s.orders());

        return res;

    }

}
