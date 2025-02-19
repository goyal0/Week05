package Json;
import com.google.gson.JsonObject;

public class MergeTwoJsonObject {
    public static void main(String[] args) {
        JsonObject j1 = new JsonObject();
        j1.addProperty("Name: " , "Pratham");

        JsonObject j2 = new JsonObject();
        j2.addProperty("City" , "Mumbai");

        for(String key : j2.keySet()){
            j1.add(key,j2.get(key));
        }
        System.out.println(j1);
    }
}
