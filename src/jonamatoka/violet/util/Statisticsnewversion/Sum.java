package jonamatoka.violet.util.Statisticsnewversion;

import java.util.ArrayList;

public class Sum extends Operation{

    Sum(IStatistic statistic){super(statistic); }

    @Override
    public double calc() {

        double sum=0;
        for(double i:statistic.calc())
            sum+=i;

        return sum;

    }

}
