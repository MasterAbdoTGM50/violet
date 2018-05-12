package jonamatoka.violet.util.Statisticsnewversion.IStatistic;
import jonamatoka.violet.util.Statisticsnewversion.Collection.Collection;

import java.util.ArrayList;
public abstract class IStatistic {
    protected Collection dataCollection;
    public IStatistic(Collection dataCollection){
        this.dataCollection=dataCollection;
    }
    public abstract ArrayList<Double> calc();
}
