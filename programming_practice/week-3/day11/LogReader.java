import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogReader {

    private final String filename;

    public LogReader(String filename) {
        this.filename = filename;
    }

    public Logs read() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));

        List<Log> logs = new ArrayList<>();
        for (String line : lines) {
            if (isNewLogLine(line)) {
                logs.add(Log.parse(line));
            } else {
                Log lastLog = logs.getLast();
                lastLog.appendMessage(line);
            }
        }

        return Logs.of(logs);
    }

    private boolean isNewLogLine(String line) {
        return line.split("\t").length == 4;
    }

}