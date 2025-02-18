import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

public class DetectDuplicates {

    public static void main(String[] args) {
        String fileName = "C:\\backup\\Week05\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\Detectfile";  // Replace with your CSV file path

        try {
            // Read the CSV file and store data based on ID
            Map<String, List<String[]>> records = readCSV(fileName);

            // Print duplicate records
            printDuplicateRecords(records);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<String[]>> readCSV(String fileName) throws IOException, CsvValidationException {
        Map<String, List<String[]>> records = new HashMap<>();

        CSVReader reader = new CSVReader(new FileReader(fileName));
        String[] header = reader.readNext();  // Read and discard the header row
        String[] line;

        while ((line = reader.readNext()) != null) {
            String id = line[0];  // Assuming ID is in the first column (index 0)
            records.putIfAbsent(id, new ArrayList<>());
            records.get(id).add(line);
        }

        reader.close();
        return records;
    }

    private static void printDuplicateRecords(Map<String, List<String[]>> records) {
        boolean foundDuplicates = false;

        for (Map.Entry<String, List<String[]>> entry : records.entrySet()) {
            if (entry.getValue().size() > 1) {  // If there are multiple records with the same ID
                foundDuplicates = true;
                System.out.println("Duplicate ID: " + entry.getKey());
                for (String[] row : entry.getValue()) {
                    System.out.println(Arrays.toString(row));
                }
                System.out.println();  // Print an empty line between duplicates
            }
        }

        if (!foundDuplicates) {
            System.out.println("No duplicates found.");
        }
    }
}
