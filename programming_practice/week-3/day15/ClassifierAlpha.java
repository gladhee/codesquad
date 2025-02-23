import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class ClassifierAlpha {

    public static Function<Integer, String> isPerfect = n -> IntStream.rangeClosed(1, n/2).filter(i -> n % i == 0).sum() == n ? "perfect" : "";

    public static Function<Integer, String> isDeficient = n -> IntStream.rangeClosed(1, n/2).filter(i -> n % i == 0).sum() < n ? "deficient" : "";

    public static Function<Integer, String> isAbundant = n -> IntStream.rangeClosed(1, n/2).filter(i -> n % i == 0).sum() > n ? "abundant" : "";

}
