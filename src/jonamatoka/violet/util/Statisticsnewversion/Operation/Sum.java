package jonamatoka.violet.util.Statisticsnewversion.Operation;

import jonamatoka.violet.util.Statisticsnewversion.IStatistic.IStatistic;

public class Sum extends Operation{

    public Sum(IStatistic statistic){super(statistic); }

    @Override
    public double calc() {

        double sum=0;
        for(double i:statistic.calc())
            sum+=i;

        return sum;

    }

}
