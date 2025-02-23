import java.util.function.Function;
import java.util.stream.IntStream;

public class PrimeAlpha {

    public static Function<Integer, String> isPrime = n ->
            n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n))
                    .noneMatch(i -> n % i == 0) ? "prime" : "";

}
