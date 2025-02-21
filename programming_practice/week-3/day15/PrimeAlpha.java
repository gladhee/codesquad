import java.util.function.Predicate;
import java.util.stream.IntStream;

public class PrimeAlpha {

    public Predicate<Integer> isPrime = n ->
            n >= 2 && IntStream.rangeClosed(2, (int) Math.sqrt(n))
                    .allMatch(i -> n % i != 0);

    public static void main(String[] args) {
        PrimeAlpha prime = new PrimeAlpha();

        System.out.println(prime.isPrime.test(10));
        System.out.println(prime.isPrime.test(7));
    }

}
