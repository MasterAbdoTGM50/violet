package jonamatoka.violet.util.Statisticsnewversion.StatQuery;

import jonamatoka.violet.util.Statisticsnewversion.IStatistic.IStatistic;
import jonamatoka.violet.util.Statisticsnewversion.Operation.*;

public class OperationFactory {
    public static final Operation getOperationQuery(String query, IStatistic iStatistic) {
        if (iStatistic != null) {
            switch (query) {
                case "Avg":
                    return new Avg(iStatistic);
                case "Max":
                    return new Max(iStatistic);
                case "Min":
                    return new Min(iStatistic);
                case "Sum":
                    return new Sum(iStatistic);
            }
        }
        return null;
    }

}
