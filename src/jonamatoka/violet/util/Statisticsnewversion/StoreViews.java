package jonamatoka.violet.util.Statisticsnewversion;

import jonamatoka.violet.data.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreViews extends   IStatistic{
    StoreViews (Collection<Store> dc){
        super(dc);
    }
    @Override
    public ArrayList<Double> calc(){

        ArrayList<Double> res = new ArrayList<>();
        List<Store> stores = dataCollection.calc();
        for(Store s: stores)
            res.add((double)s.views());

        return res;

    }

}
