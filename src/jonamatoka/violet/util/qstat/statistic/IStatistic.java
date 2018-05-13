package jonamatoka.violet.util.qstat.statistic;
import jonamatoka.violet.util.qstat.collection.Collection;

import java.util.ArrayList;
public abstract class IStatistic {
    protected Collection dataCollection;
    public IStatistic(Collection dataCollection){
        this.dataCollection=dataCollection;
    }
    public abstract ArrayList<Double> calc();
}
