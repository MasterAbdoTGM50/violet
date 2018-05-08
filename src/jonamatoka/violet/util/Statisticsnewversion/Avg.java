package jonamatoka.violet.util.Statisticsnewversion;

import java.util.ArrayList;

public class Avg extends Operation{

    Avg(IStatistic statistic){
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
