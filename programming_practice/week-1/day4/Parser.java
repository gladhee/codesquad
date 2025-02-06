import java.util.Arrays;
import java.util.List;

public class Parser {

    public static List<Integer> parseYearMonthAndCapacity(String line) {
        try {
            return Arrays.stream(line.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter two numbers separated by a comma.");
        }
    }

}
