import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Application {

    private static final Function<Integer, String> isSquared = n ->
            IntStream.rangeClosed(1, (int) Math.sqrt(n))
                    .anyMatch(i -> i * i == n) ? "squared" : "";

    private static final Consumer<String> print = System.out::println;

    public static void main(String[] args) {
        IntStream.rangeClosed(2, 100)
                .forEach(n -> {
                    String properties = Stream.of(
                                    ClassifierAlpha.isPerfect.apply(n),
                                    ClassifierAlpha.isDeficient.apply(n),
                                    ClassifierAlpha.isAbundant.apply(n),
                                    PrimeAlpha.isPrime.apply(n),
                                    isSquared.apply(n)
                            )
                            .filter(s -> !s.isEmpty())
                            .reduce((s1, s2) -> s1 + ", " + s2)
                            .orElse("");

                    print.accept(n + ": " + properties);
                });
    }

}