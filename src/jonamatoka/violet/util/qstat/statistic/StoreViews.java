package jonamatoka.violet.util.qstat.statistic;

import jonamatoka.violet.data.model.Store;
import jonamatoka.violet.util.qstat.collection.Collection;

import java.util.ArrayList;
import java.util.List;

public class StoreViews extends   IStatistic{
    public StoreViews (Collection<Store> dc){
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
