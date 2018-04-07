package jonamatoka.violet.util.stat;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class AVERAGE implements StatOperation {
    @Override
    public String operation(List<JsonNode> values) {
        Double sum=0.0 ;
        for(int i=0;i<values.size();++i){
            sum+=values.get(i).asDouble();
        }
        return String.valueOf(sum/values.size());
    }

}
