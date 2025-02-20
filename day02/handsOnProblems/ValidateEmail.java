package Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.File;

public class ValidateEmail {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the user JSON and schema JSON
            JsonNode userNode = objectMapper.readTree(new File("user.json"));
            JsonNode schemaNode = objectMapper.readTree(new File("schema.json"));

            // Create JSON Schema from the schema file
            JsonSchemaFactory jsonFactory = JsonSchemaFactory.byDefault();
            JsonSchema jsonSchema = jsonFactory.getJsonSchema(schemaNode);

            // Validate the entire user JSON (not just "email")
            ProcessingReport report = jsonSchema.validate(userNode);

            // Check validation result
            if (report.isSuccess()) {
                System.out.println("Email is valid");
            } else {
                System.out.println("Email is not valid");
                System.out.println(report);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error during JSON validation: " + e.getMessage(), e);
        } finally {
            System.out.println("Operation Executed Successfully");
        }
    }
}
