package Json;

import com.google.gson.JsonObject;


public class JsonObjectExample {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name","Pratham");
        jsonObject.addProperty("age","28");
        jsonObject.addProperty("email","Pratham@123");

        System.out.println(jsonObject.toString());
    }
}
