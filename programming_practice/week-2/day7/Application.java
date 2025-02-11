import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
//        String[] input = {"PRINT", "PUSH0", "PRINT", "POPA"}; // ==> return ["EMPTY", "0", "EMPTY"]
//        String[] input = {"PUSH1", "PUSH1", "PUSH2", "POPA", "POPB", "SWAP", "ADD", "PRINT", "PRINT"}; // ==> return ["3", "1"]
//        String[] input = {"PUSH3", "PUSH2", "PUSH1", "POPA", "POPB", "SWAP", "SUB", "POPA", "POPB", "ADD", "PRINT"}; // ==> return ["4"]
        String[] input = {"ADD", "PUSH3", "PUSH1", "PUSH0", "PUSH2", "PUSH1", "PUSH3", "PUSH2", "PUSH0", "PUSH3", "PUSH4"}; // ==> return ["ERROR", "OVERFLOW", "UNKNOWN"]

        dequeTest(input);
        System.out.println("====================================");
        listTest(input);
    }

    private static void dequeTest(String[] input) {
        System.out.println("Deque Test");

        List<String> answer = new ArrayList<>();
        MyStack<Integer> stack = new MyDeque<>();
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

    private static void listTest(String[] input) {
        System.out.println("List Test");

        List<String> answer = new ArrayList<>();
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
