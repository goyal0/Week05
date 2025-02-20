package Json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class JsonArray {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<Student> ls = new ArrayList<>();
            Student s1 = new Student("Nishant", 21);
            Student s2 = new Student("Pankaj", 22);
            Student s3 = new Student("Uday", 24);
            ls.add(s1);
            ls.add(s2);
            ls.add(s3);
            String jsonArraytoString=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ls);
            System.out.println("Value as array");
            System.out.println(jsonArraytoString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Operation Executed Susccesffully");
        }
    }
}