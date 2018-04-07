package jonamatoka.violet.util.stat;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class MAX implements StatOperation {

    @Override
    public String operation(List<JsonNode> values) {
        JsonNode max= values.get(0);
        int maxValueIndex=0 ;
        for(int i=1;i<values.size();++i){
            if (max.asDouble()<values.get(i).asDouble()){
                max= values.get(i);
                maxValueIndex= i;
            }
        }
        return String.valueOf(maxValueIndex);
    }
}
