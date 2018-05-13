package jonamatoka.violet.util.qstat.query;

import jonamatoka.violet.util.qstat.collection.Collection;
import jonamatoka.violet.util.qstat.statistic.*;

public class IStatisticFactory {
    public static final IStatistic getIStatisticQuery(String query, Collection collection) {
        if (collection != null) {
            switch (query) {
                case "ProductOrders":
                    return new ProductOrders(collection);
                case "ProductViews":
                    return new ProductViews(collection);
                case "StoreOrders":
                    return new StoreOrders(collection);
                case "StoreViews":
                    return new StoreViews(collection);
            }
        }
        return null;
    }

}
