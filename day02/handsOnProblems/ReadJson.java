package Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;

public class ReadJson {
    public static void main(String[] args) throws Exception {
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            JsonNode readjson=objectMapper.readTree(new File("ReadandPrint.json"));
            System.out.println(readjson);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
