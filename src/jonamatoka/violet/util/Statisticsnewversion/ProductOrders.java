package jonamatoka.violet.util.Statisticsnewversion;

import jonamatoka.violet.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductOrders extends IStatistic {

    ProductOrders(Collection<Product> dc){
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
