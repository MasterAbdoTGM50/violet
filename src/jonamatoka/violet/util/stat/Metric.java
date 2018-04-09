package jonamatoka.violet.util.stat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jonamatoka.violet.util.stat.op.StatOp;

import java.io.IOException;
import java.util.List;

public class Metric {

    private JsonNode jNode;

    public Metric(String jsonString) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            jNode = mapper.readTree(jsonString);

        } catch (IOException e) { e.printStackTrace(); }

    }

    /* TODO//Saka: Rethink logic */
    public String getMetric(String opField, StatOp op, String outField) {

        String[] myFields = opField.split("/");

        int i=0;
        for(; i < myFields.length - 1 && jNode != null; ++i) { jNode = jNode.findValue(myFields[i]); }

        if(jNode !=null) {
            List<JsonNode> jValues = jNode.findValues(myFields[i]);
            String opRes = op.operate(jValues);
            if(outField.length() > 0) { return jNode.get(Integer.parseInt(opRes)).findValue(outField).asText(); }
            return opRes;
        }

        return "";

    }

}
