package jonamatoka.violet.util.stat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Metrics {
    private JsonNode JNode;

    public Metrics(String jsonString){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(jsonString, JsonNode.class);
        try {
            JNode = mapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getMetics(String field /*products/price*/,StatOperation op,String outField){
        String[] myFields= field.split("/");
        int i=0;
        for( ;i<myFields.length-1 && JNode!=null;++i) {
            JNode = JNode.findValue(myFields[i]);
        }
        if(JNode!=null)
        {
            List<JsonNode> JValues= JNode.findValues(myFields[i]); //put all values of the last field in a list
            String opRes =op.operation(JValues);
            if(outField.length()>0)
                return JNode.get(Integer.parseInt(opRes)).findValue(outField).asText();
            return opRes;
        }
        return "error 404 not found!!";
    }
}
