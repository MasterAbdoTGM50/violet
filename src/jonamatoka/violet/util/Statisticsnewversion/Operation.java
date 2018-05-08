package jonamatoka.violet.util.Statisticsnewversion;

import java.util.ArrayList;

public abstract class Operation {

 protected IStatistic statistic;

 Operation(IStatistic statistic){
     this.statistic=statistic;
 }

  abstract double calc();

}
