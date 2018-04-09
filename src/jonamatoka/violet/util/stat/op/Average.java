package jonamatoka.violet.util.stat.op;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Average implements StatOp {
    @Override
    public String operate(List<JsonNode> nodes) {

        Double sum = 0.0;
        for(JsonNode node : nodes) { sum += node.asDouble(); }
        return String.valueOf(sum / nodes.size());

    }

}
