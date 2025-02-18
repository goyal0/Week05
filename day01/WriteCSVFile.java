import  com.opencsv.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCSVFile {
    public static void main(String[] args) throws IOException {
        String path="writecsv.csv";
        try(CSVWriter writer = new CSVWriter(new FileWriter(path), CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)){

            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            String [] data = {"Name","Age","Gender","Country"};
            writer.writeNext(data);
            String [] data1 = {"Amit","20","male","India"};
            writer.writeNext(data1);
            String [] data2 = {"Raj","25","male","USA"};
            writer.writeNext(data2);
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
