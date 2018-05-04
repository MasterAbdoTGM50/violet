package jonamatoka.violet.util.stat.op;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Min implements StatOp {

    @Override
    public String operate(List<JsonNode> nodes) {

        JsonNode min = nodes.get(0);

        int mini = 0;
        for(int i = 1; i < nodes.size(); ++i) {

            if(min.asDouble() > nodes.get(i).asDouble()) {
                min = nodes.get(i);
                mini = i;
            }

        }

        return String.valueOf(mini);

    }

}
