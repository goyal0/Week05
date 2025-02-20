package Json;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.print.DocFlavor;
import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class JsonDatabase {
    public static void main(String[] args) throws Exception{
        String url="jdbc:mysql://localhost:3306/COLLEGE";
        String username="root";
        String password="root";
        String query="SELECT * FROM STUDENT";
        try{
             Connection connection=DriverManager.getConnection(url,username,password);
            Statement st=connection.createStatement();
            ResultSet res=st.executeQuery(query);
            List<Map<String, Object>>listdata=new ArrayList<>();
            while (res.next()){
                Map<String, Object>mp=new HashMap<>();
                mp.put("Enrollement",res.getString("Enrollment"));
                mp.put("id is ",res.getInt("id"));
                mp.put("semester is ",res.getInt("semester"));
                listdata.add(mp);

            }
            ObjectMapper objectMapper=new ObjectMapper();
            String jsondata=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listdata);
            System.out.println("The json data value of the Database record is "+jsondata);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Operation executed Sucsessfully");
        }


    }
}
