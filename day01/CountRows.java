import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountRows {
    public static void main(String[] args) {
            //file path
        String filePath = "C:\\ideaProjects\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\employeefile";
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line = br.readLine(); // Read the header line and ignore it

            while ((line = br.readLine()) != null) {
                    count++; // Count records (excluding the header)
            }
                System.out.println("Total number of records (excluding header): " + count);
        } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
        }

    }
}


