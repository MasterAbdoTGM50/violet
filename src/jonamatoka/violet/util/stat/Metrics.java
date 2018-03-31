package jonamatoka.violet.util.stat;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public class Metrics {
    public String getMetics(JsonNode JNode,String field /*products/price*/,StatOperation op,String outField){
        String[] myFields= field.split("/");
        JsonNode myJNode= JNode;
        int i=0;
        for( ;i<myFields.length-1 && myJNode!=null;++i) {
            myJNode = myJNode.findValue(myFields[i]);
        }
        if(myJNode!=null)
        {
            List<JsonNode> JValues= myJNode.findValues(myFields[i]); //put all values of the last field in a list
            String opRes =op.operation(JValues);
            if(outField.length()>0)
                return myJNode.get(Integer.parseInt(opRes)).findValue(outField).asText();
            return opRes;
        }
        return "error 404 not found!!";
    }
}
