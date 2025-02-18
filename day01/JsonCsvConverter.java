import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;
import java.io.*;
import java.util.*;

public class JsonCsvConverter {
    public static void main(String[] args) throws IOException {
        String jsonFile = "students.json";
        String csvFile = "students.csv";
        
        // Convert JSON to CSV
        jsonToCsv(jsonFile, csvFile);
        
        // Convert CSV back to JSON
        csvToJson(csvFile, "students_converted.json");
    }
     // Converts a JSON file to CSV format.
    
    public static void jsonToCsv(String jsonFile, String csvFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MappingIterator<Map<String, String>> iterator = objectMapper.readValue(new File(jsonFile), new TypeReference<List<Map<String, String>>>() {});

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.builder()
                .setUseHeader(true)
                .addColumns(iterator.next().keySet(), CsvSchema.ColumnType.STRING)
                .build();
        
        csvMapper.writer(schema).writeValue(new File(csvFile), iterator);
        System.out.println("JSON converted to CSV successfully.");
    }

    
     // Converts a CSV file back to JSON format.
  
    public static void csvToJson(String csvFile, String jsonFile) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader reader = csvMapper.readerFor(Map.class).with(schema);
        
        MappingIterator<Map<String, String>> iterator = reader.readValues(new File(csvFile));
        List<Map<String, String>> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFile), list);
        System.out.println("CSV converted to JSON successfully.");
    }
}