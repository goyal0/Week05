import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchRecord {
    public static void main(String[] args) {
            String filePath = "C:\\ideaProjects\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\employeefile"; // file path
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter employee name to search: ");
            String searchName = scanner.nextLine(); // Read user input
            boolean found = false;

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line = br.readLine(); // Read the header line

                while ((line = br.readLine()) != null) {
                    String[] column = line.split(",");

                    String empName = column[1].trim(); // Get employee name

                    if (empName.equalsIgnoreCase(searchName)) { // Case-insensitive search
                        System.out.println("Employee Found!");
                        System.out.println("Department: " + column[2]);
                        System.out.println("Salary: $" + column[3]);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee not found.");
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
    }
}


