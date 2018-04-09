package jonamatoka.violet.util.stat.op;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Max implements StatOp {

    @Override
    public String operate(List<JsonNode> nodes) {

        JsonNode max = nodes.get(0);

        int maxi = 0;
        for(int i = 1; i < nodes.size(); ++i) {

            if(max.asDouble() < nodes.get(i).asDouble()) {
                max = nodes.get(i);
                maxi = i;
            }

        }

        return String.valueOf(maxi);

    }

}
