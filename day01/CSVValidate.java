import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVValidate {

    // Regular expression for validating email
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Function to validate phone number (exactly 10 digits)
    private static boolean validatePhoneNumber(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    // Function to validate email
    private static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // Function to process and validate the CSV file
    public static void validateCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;

            // Skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                row++;
                String[] columns = line.split(",");

                if (columns.length < 2) {
                    System.out.println("Row " + row + " is invalid: Not enough columns.");
                    continue;
                }

                String email = columns[0].trim(); // Assuming Email is in the first column
                String phone = columns[1].trim(); // Assuming Phone Number is in the second column

                boolean isEmailValid = validateEmail(email);
                boolean isPhoneValid = validatePhoneNumber(phone);

                if (!isEmailValid || !isPhoneValid) {
                    System.out.println("Invalid row " + row + ": " +
                            "Email valid: " + isEmailValid + ", " +
                            "Phone valid: " + isPhoneValid);
                    System.out.println("Row: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\backup\\Week05\\CsvHandling\\src\\main\\java\\com\\capgeminitraining\\week5\\csvvalidatorfile"; // file path
        validateCSV(filePath);
    }
}
