package jonamatoka.violet.util.qstat.op;

import jonamatoka.violet.util.qstat.statistic.IStatistic;

public abstract class Operation {

 protected IStatistic statistic;

    public Operation(IStatistic statistic){
     this.statistic=statistic;
    }

  abstract double calc();

}
