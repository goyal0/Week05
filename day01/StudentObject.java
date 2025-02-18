import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StudentObject {

    public static List<Student> readCSV(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            // Skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] columns = line.split(",");

                if (columns.length < 3) {
                    System.out.println("Row " + lineNumber + " is invalid: Not enough columns.");
                    continue;
                }

                String name = columns[0].trim(); //  Name is in the first column
                String email = columns[1].trim(); // Email is in the second column
                String phone = columns[2].trim(); // Phone Number is in the third column

                // Create Student object
                Student student = new Student(name, email, phone);

                // Add to list
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void main(String[] args) {
        String filePath = "C:\\backup\\Week05\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\studentfile"; //  file path
        List<Student> students = readCSV(filePath);

        // Print all Student objects
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static class Student {
        private String name;
        private String email;
        private String phoneNumber;

        // Constructor
        public Student(String name, String email, String phoneNumber) {
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        // Getters and Setters (optional, but useful for access)
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        // Override toString for easy printing
        @Override
        public String toString() {
            return "Student{name='" + name + "', email='" + email + "', phoneNumber='" + phoneNumber + "'}";
        }
    }
}
