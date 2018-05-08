package jonamatoka.violet.util.Statisticsnewversion;
import java.util.ArrayList;
public abstract class IStatistic {
    protected Collection dataCollection;
    IStatistic(Collection dataCollection){
        this.dataCollection=dataCollection;
    }
    public abstract ArrayList<Double> calc();
}
