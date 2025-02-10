import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberGenerator {

    private NumberGenerator() {
    }

    public static List<Integer> pickUniqueNumbersInRange(int startInclusive, int endInclusive, int count) {
        List<Integer> numbers = new ArrayList<>();

        for(int i = startInclusive; i <= endInclusive; ++i) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        return numbers;
    }

}
