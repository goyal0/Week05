import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilterRecords {
    public static void main(String[] args) {
        String filePath = "C:\\ideaProjects\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\studentcsv";
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean isHeader = true;
                while ((line = br.readLine()) != null) {
                    String[] column= line.split(",");
                    // Print header
                    if (isHeader) {
                        System.out.printf("%-5s %-15s %-15s %-6s%n", column[0], column[1], column[2], column[3]);
                        System.out.println("---------------------------------------------");
                        isHeader = false;
                        continue;
                    }

                    int marks = Integer.parseInt(column[3]); // Convert marks to integer

                    // Print only students who scored more than 80
                    if (marks > 80) {
                        System.out.printf("%-5s %-15s %-15s %-6s%n", column[0], column[1], column[2], column[3]);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

    }

}
