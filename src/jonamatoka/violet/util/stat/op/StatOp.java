package jonamatoka.violet.util.stat.op;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface StatOp {

    String operate(List<JsonNode> nodes);

}
