import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PrimeAlpha {
    private int number;

    public PrimeAlpha(int number) {
        this.number = number;
    }

    public boolean isPrime() {
        Set<Integer> primeSet = new HashSet<Integer>(){ {add(1); add(number);} };
        return number > 1 && factors().equals(primeSet);
    }

    public boolean isFactor(int potentialFactor) {
        return number % potentialFactor == 0;
    }

    public Set<Integer> factors() {
        HashSet<Integer> factors = new HashSet<>();
        for (int pod=1; pod <= Math.sqrt(number); pod++) {
            if (isFactor(pod)) {
                factors.add(pod);
                factors.add(number / pod);
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        PrimeAlpha prime1 = new PrimeAlpha(10);
        PrimeAlpha prime2 = new PrimeAlpha(7);

        System.out.println(prime1.isPrime());
        System.out.println(prime2.isPrime());
    }
}
