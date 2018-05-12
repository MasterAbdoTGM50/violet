package jonamatoka.violet.util.Statisticsnewversion.Operation;

import jonamatoka.violet.util.Statisticsnewversion.IStatistic.IStatistic;

public class Avg extends Operation {

    public Avg(IStatistic statistic){
        super(statistic);
    }

    @Override
    public double calc() {

        double avg=0,siz=0;
        for(double i:statistic.calc()) {
            avg += i;
            ++siz;
        }

        return avg/siz;

    }

}
