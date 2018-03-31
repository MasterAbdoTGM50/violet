package jonamatoka.violet.util.stat;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class MIN implements StatOperation {

    @Override
    public String operation(List<JsonNode> values) {
        JsonNode min= values.get(0);
        int minValueIndex=0 ;
        for(int i=1;i<values.size();++i){
            if (min.asDouble()>values.get(i).asDouble()){
                min= values.get(i);
                minValueIndex=i;
            }
        }
        return String.valueOf(minValueIndex);
    }
}
