import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.*;
import java.util.Base64;

public class EncryptandDecrypt {
    private static final String SECRET_KEY = "1234567890123456"; // 16-char key for AES encryption

    public static void main(String[] args) throws Exception {
        String jsonFile = "students.json";
        String csvFile = "students.csv";
        
        // Convert JSON to encrypted CSV
        jsonToCsv(jsonFile, csvFile);
        
        // Convert encrypted CSV back to JSON
        csvToJson(csvFile, "students_converted.json");
    }

    
    // Encrypts a given text using AES encryption.
     
    private static String encrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
    }

    
      Decrypts a given encrypted text using AES decryption.
     
    private static String decrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(value)));
    }

    
     // Converts a JSON file to an encrypted CSV format.
     
    public static void jsonToCsv(String jsonFile, String csvFile) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> data = objectMapper.readValue(new File(jsonFile), new TypeReference<List<Map<String, String>>>() {});
        
        // Encrypt sensitive fields (Salary, Email)
        for (Map<String, String> row : data) {
            if (row.containsKey("Salary")) row.put("Salary", encrypt(row.get("Salary")));
            if (row.containsKey("Email")) row.put("Email", encrypt(row.get("Email")));
        }
        
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.builder()
                .setUseHeader(true)
                .addColumns(data.get(0).keySet(), CsvSchema.ColumnType.STRING)
                .build();
        
        csvMapper.writer(schema).writeValue(new File(csvFile), data);
        System.out.println("JSON converted to encrypted CSV successfully.");
    }

    
     // Converts an encrypted CSV file back to JSON format.
     
    public static void csvToJson(String csvFile, String jsonFile) throws Exception {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader reader = csvMapper.readerFor(Map.class).with(schema);
        
        MappingIterator<Map<String, String>> iterator = reader.readValues(new File(csvFile));
        List<Map<String, String>> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map<String, String> row = iterator.next();
            
            // Decrypt sensitive fields (Salary, Email)
            if (row.containsKey("Salary")) row.put("Salary", decrypt(row.get("Salary")));
            if (row.containsKey("Email")) row.put("Email", decrypt(row.get("Email")));
            
            list.add(row);
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFile), list);
        System.out.println("Encrypted CSV converted back to JSON successfully.");
    }
}
