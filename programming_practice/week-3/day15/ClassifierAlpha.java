import java.util.function.Predicate;
import java.util.stream.IntStream;

public class ClassifierAlpha {

    public Predicate<Integer> isPerfect = n -> IntStream.rangeClosed(1, n / 2).filter(i -> n % i == 0).sum() == n;

    public Predicate<Integer> isDeficient =  n -> IntStream.rangeClosed(1, n/2).filter(i -> n % i == 0).sum() < n;

    public Predicate<Integer> isAbundant = n -> IntStream.rangeClosed(1, n/2).filter(i -> n % i == 0).sum() > n;

    public static void main(String[] args) {
        ClassifierAlpha alpha = new ClassifierAlpha();

        System.out.println(alpha.isPerfect.test(10));
        System.out.println(alpha.isPerfect.test(6));
    }

}
