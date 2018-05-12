package jonamatoka.violet.util.qstat.op;

import jonamatoka.violet.util.qstat.statistic.IStatistic;

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
