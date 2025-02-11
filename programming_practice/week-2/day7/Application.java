import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<String> answer = new ArrayList<>();
        String[] input = {"ADD", "PUSH3", "PUSH1", "PUSH0", "PUSH2", "PUSH1", "PUSH3", "PUSH2", "PUSH0", "PUSH3", "PUSH4"};
        MyStack<Integer> stack = new MyList<>();
        StackCalculator calculator = new StackCalculator(stack);

        for (String command : input) {
            String ret = calculator.run(command);
            if (ret.isEmpty()) {
                continue;
            }
            answer.add(ret);
        }

        System.out.println(answer);
    }

}
