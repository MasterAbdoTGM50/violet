package jonamatoka.violet.util.qstat.statistic;


import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.util.qstat.collection.Collection;

import java.util.ArrayList;

import java.util.List;

public class ProductViews extends IStatistic {

    public ProductViews(Collection<Product> dc){
        super(dc);
    }

    @Override
    public ArrayList<Double> calc(){

        ArrayList<Double> res = new ArrayList<>();
        List<Product> products = dataCollection.calc();
        for(Product p: products)
            res.add((double)p.views());

        return res;

    }

}
