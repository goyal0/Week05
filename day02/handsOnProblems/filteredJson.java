package Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class filteredJson {
    public static void main(String[] args) throws Exception {
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            JsonNode jsonNode=objectMapper.readTree(new File("filter.json"));
            if(jsonNode.isArray()){
                for(JsonNode node:jsonNode){
                    if(node.has("age")&&node.get("age").asInt()>25){
                        Iterator<Map.Entry<String,JsonNode>>iterator=node.fields();
                        while (iterator.hasNext()){
                            Map.Entry<String,JsonNode>field=iterator.next();
                            System.out.println("key ="+field.getKey()+" value = "+field.getValue());
                        }
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Operation Executed Sucsessfully");
        }
    }
}
