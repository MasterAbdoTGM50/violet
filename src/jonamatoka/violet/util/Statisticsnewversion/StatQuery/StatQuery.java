package jonamatoka.violet.util.Statisticsnewversion.StatQuery;

import jonamatoka.violet.util.Statisticsnewversion.Collection.Collection;
import jonamatoka.violet.util.Statisticsnewversion.IStatistic.IStatistic;
import jonamatoka.violet.util.Statisticsnewversion.Operation.Operation;

//Avg:storeviews:stiresbyid‹123›
//Max:ProductOrders:Products<>
public class StatQuery {
    private String[] query;

    public StatQuery() {
    }

    public StatQuery(String query) {
        this.query = query.split(":");
    }

    public void setQuery(String query) {
        this.query = query.split(":");
    }

    public Operation getStatQuery() {
        try {
            String id;
            //get id between '<' '>'
            id = query[2].substring(query[2].indexOf('<') + 1, query[2].indexOf('>'));

            CollectionFactory collectionFactory = new CollectionFactory();

            //get collection object from collectionFactory
            Collection collection = collectionFactory.getIStatisticQuery(query[2].substring(0, query[2].indexOf('<')), id);

            //get IStatistic object from IStatisticFactory
            IStatistic statistic = IStatisticFactory.getIStatisticQuery(query[1], collection);

            //get Operation object from OperationFactory
            Operation operation = OperationFactory.getOperationQuery(query[0], statistic);
            return operation;
        } catch (NullPointerException e) {
            System.out.print("Wrong query !!");
        }
        return null;
    }

}
