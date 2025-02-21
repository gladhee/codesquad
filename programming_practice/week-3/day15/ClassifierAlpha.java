import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClassifierAlpha {
    private int number;

    public ClassifierAlpha(int number) {
        this.number = number;
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

    static public int sum(Set<Integer> factors) {
        Iterator iterator = factors.iterator();
        int sum = 0;
        while (iterator.hasNext()) {
            sum += (Integer) iterator.next();
        }
        return sum;
    }

    public boolean isPerfect() {
        return sum(factors()) - number == number;
    }

    public boolean isAbundant() {
        return sum(factors()) - number > number;
    }

    public boolean isDeficient() {
        return sum(factors()) - number < number;
    }
    public static void main(String[] args) {
        ClassifierAlpha alpha1 = new ClassifierAlpha(10);
        ClassifierAlpha alpha2 = new ClassifierAlpha(6);

        System.out.println(alpha1.isPerfect());
        System.out.println(alpha2.isPerfect());
    }
}
