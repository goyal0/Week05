package Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class MergeJson {
    public static void main(String[] args) throws Exception{
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            JsonNode node1=objectMapper.readTree(new File("json1.json"));
            JsonNode node2=objectMapper.readTree(new File("json2.json"));
            ObjectNode mergeJson=objectMapper.createObjectNode();
            mergeJson.setAll((ObjectNode) node1 );
            mergeJson.setAll((ObjectNode) node2);
            objectMapper.writeValue(new File("mergedjson.json"),mergeJson);
            System.out.println("Sucessfully json written in the Json");
            System.out.println(mergeJson.toPrettyString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Operation Executed Sucessfully");
        }

    }
}
