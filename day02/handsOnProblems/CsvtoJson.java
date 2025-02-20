package Json;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvtoJson {
    public static void main(String[] args)throws Exception {
        String filepath="data.csv";
        try{
            FileReader fileReader=new FileReader(filepath);
            CSVParser csvParser=new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader());
            List<Map<String,String>>jsonList=new ArrayList<>();
            for(CSVRecord record:csvParser){
                jsonList.add(record.toMap());
            }
            ObjectMapper objectMapper=new ObjectMapper();
            String JSONOutput=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper);
            System.out.println("Json value printed");
            System.out.println(JSONOutput);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Operation Executed Sucsessfully");
        }

    }
}
