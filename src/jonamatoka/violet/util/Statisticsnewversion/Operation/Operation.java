package jonamatoka.violet.util.Statisticsnewversion.Operation;

import jonamatoka.violet.util.Statisticsnewversion.IStatistic.IStatistic;

public abstract class Operation {

 protected IStatistic statistic;

    public Operation(IStatistic statistic){
     this.statistic=statistic;
    }

  abstract double calc();

}
