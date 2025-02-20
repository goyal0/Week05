package Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;

public class JsontoXML {
    public static void main(String[] args) throws Exception{
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            JSONObject readjson=new JSONObject(objectMapper.readTree(new File("ReadandPrint.json")).toString());
            String XMLdata= XML.toString(readjson);
            System.out.println("XML Data is "+XMLdata);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("XML Value printed");
        }

    }
}
