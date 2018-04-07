package jonamatoka.violet.util.stat;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface StatOperation{
    String operation(List<JsonNode>values);
}
