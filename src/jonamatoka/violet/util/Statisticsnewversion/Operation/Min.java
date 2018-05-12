package jonamatoka.violet.util.Statisticsnewversion.Operation;

import jonamatoka.violet.util.Statisticsnewversion.IStatistic.IStatistic;


public class Min extends Operation{

    public Min(IStatistic statistic){ super(statistic); }

    @Override
    public double calc() {

        double min=0,ind=0;
        for(double i:statistic.calc()) {
            if (ind == 0) {
                min = i;
                ++ind;
                continue;
            }
            if (i < min)
                min = i;
        }

        return min;

    }

}
