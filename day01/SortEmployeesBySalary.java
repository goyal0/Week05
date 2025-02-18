import java.io.*;
import java.util.*;

public class SortEmployeesBySalary {
    public static void main(String[] args) {
        //file path
        String filePath = "C:\\backup\\Week05\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\employeecsvfile";
        List<String[]> employees = readAndSortEmployees(filePath);

        if (employees != null) {
            System.out.println("\nTop 5 Highest Paid Employees:");
            System.out.printf("%-5s %-15s %-12s %-8s%n", "ID", "Name", "Department", "Salary");
            // Print the top 5 employees 
            for (int i = 0; i < Math.min(5, employees.size()); i++) {
                String[] emp = employees.get(i);
                System.out.printf("%-5s %-15s %-12s $%-7s%n", emp[0], emp[1], emp[2], emp[3]);
            }
        }
    }

    public static List<String[]> readAndSortEmployees(String filePath) {
        List<String[]> employeeList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header row

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                employeeList.add(data);
            }

            // Sort by salary in descending order
            employeeList.sort((a, b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }

        return employeeList;
    }
}

