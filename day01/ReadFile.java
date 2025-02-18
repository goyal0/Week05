import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        String filePath = "C:\\ideaProjects\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\csvfile"; // Change this to your file path

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean isHeader = true;

                while ((line = br.readLine()) != null) {
                    String[] column = line.split(",");

                    // Print header with formatting
                    if (isHeader) {
                        System.out.printf("%-5s %-15s %-15s %-6s%n", column[0], column[1], column[2], column[3]);
                        isHeader = false;
                        continue;
                    }

                    // Print formatted student details
                    System.out.printf("%-5s %-15s %-15s %-6s%n", column[0], column[1], column[2],column[3]);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }


}
