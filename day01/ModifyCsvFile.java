import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ModifyCsvFile {
    public static void main(String[] args) {
            String inputFile = "C:\\ideaProjects\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\employeefile"; // Original file
            String outputFile = "C:\\ideaProjects\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\updatedemployeefile"; // New file with updated salaries
            List<String[]> employeeData = new ArrayList<>();

            try (BufferedReader br = Files.newBufferedReader(Paths.get(inputFile))) {
                String line;
                boolean isHeader = true;

                while ((line = br.readLine()) != null) {
                    String[] column= line.split(",");

                    // Add header without modification
                    if (isHeader) {
                        employeeData.add(column);
                        isHeader = false;
                        continue;
                    }

                    // Check if the employee is from IT department
                    if (column[2].equalsIgnoreCase("IT")) {
                        double salary = Double.parseDouble(column[3]); // Parse salary
                        salary *= 1.10; // Increase by 10%
                        column[3] = String.format("%.2f", salary); // Format salary to 2 decimal places
                    }

                    employeeData.add(column);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                return;
            }

            // Write updated data to new CSV file
            try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outputFile))) {
                for (String[] row : employeeData) {
                    bw.write(String.join(",", row));
                    bw.newLine();
                }
                System.out.println("Updated salaries saved to " + outputFile);
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
            }
    }


}
