import java.io.IOException;
import java.io.PrintWriter;

public class Application {

    public static void main(String[] args) {
        String filename = "Masters 2025 Day 11 Log Analysis.log";
        LogReader fileReader = new LogReader(filename);

        try {
            Logs logs = fileReader.read();
            Filter input = new Filter(logs);

            Logs filteredByInput = input.filter();

            createReport(filteredByInput);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void createReport(Logs logs) {
        try (PrintWriter writer = new PrintWriter("report.log")) {
            writer.print(logs);
            System.out.println("Report created successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

}
