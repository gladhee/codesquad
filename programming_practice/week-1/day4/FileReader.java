import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    public static List<String> readLines(String filename) {
        InputStream inputStream = getInputStream(filename);
        return readLinesFromStream(inputStream);
    }

    private static InputStream getInputStream(String filename) {
        InputStream inputStream = FileReader.class.getClassLoader()
                .getResourceAsStream(filename);
        validateInputStream(inputStream, filename);
        return inputStream;
    }

    private static void validateInputStream(InputStream inputStream, String filename) {
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + filename);
        }
    }

    private static List<String> readLinesFromStream(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}