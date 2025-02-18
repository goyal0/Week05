import java.io.*;
import java.util.*;

public class MergedTwoFiles {
    public static void main(String[] args) {
        String file1 = "C:\\backup\\Week05\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\File1"; // Update with actual path
        String file2 = "C:\\backup\\Week05\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\File2"; // Update with actual path
        String mergedFile = "C:\\backup\\Week05\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\output file";

        Map<String, String[]> studentData = new HashMap<>();

        // Read first CSV file (ID, Name, Age)
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) { // Skip header
                    isHeader = false;
                    continue;
                }
                String[] columns = line.split(",");
                studentData.put(columns[0], new String[]{columns[1], columns[2], "", ""}); // Store Name, Age
            }
        } catch (IOException e) {
            System.err.println("Error reading " + file1 + ": " + e.getMessage());
        }

        // Read second CSV file (ID, Marks, Grade)
        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) { // Skip header
                    isHeader = false;
                    continue;
                }
                String[] columns = line.split(",");
                if (studentData.containsKey(columns[0])) {
                    studentData.get(columns[0])[2] = columns[1]; // Store Marks
                    studentData.get(columns[0])[3] = columns[2]; // Store Grade
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading " + file2 + ": " + e.getMessage());
        }

        // Write to merged CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(mergedFile))) {
            // Write header
            bw.write("ID,Name,Age,Marks,Grade");
            bw.newLine();

            // Write merged data
            for (Map.Entry<String, String[]> entry : studentData.entrySet()) {
                String[] data = entry.getValue();
                bw.write(entry.getKey() + "," + data[0] + "," + data[1] + "," + data[2] + "," + data[3]);
                bw.newLine();
            }

            System.out.println("Merged file created: " + mergedFile);
        } catch (IOException e) {
            System.err.println("Error writing merged file: " + e.getMessage());
        }
    }
}