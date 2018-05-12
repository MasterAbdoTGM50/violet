package jonamatoka.violet.util.Statisticsnewversion.IStatistic;

import jonamatoka.violet.data.model.Product;
import jonamatoka.violet.util.Statisticsnewversion.Collection.Collection;

import java.util.ArrayList;
import java.util.List;

public class ProductOrders extends IStatistic {

    public ProductOrders(Collection<Product> dc){
        super(dc);
    }

    @Override
    public ArrayList<Double> calc(){

        ArrayList<Double> res = new ArrayList<>();
        List<Product> products = dataCollection.calc();
        for(Product p: products)
            res.add((double)p.orders());

        return res;

    }

}
