import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
//        String[] input = {"PRINT", "PUSH0", "PRINT", "POPA"}; // ==> return ["EMPTY", "0", "EMPTY"]
//        String[] input = {"PUSH1", "PUSH1", "PUSH2", "POPA", "POPB", "SWAP", "ADD", "PRINT", "PRINT"}; // ==> return ["3", "1"]
//        String[] input = {"PUSH3", "PUSH2", "PUSH1", "POPA", "POPB", "SWAP", "SUB", "POPA", "POPB", "ADD", "PRINT"}; // ==> return ["4"]
        String[] input = {"ADD", "PUSH3", "PUSH1", "PUSH0", "PUSH2", "PUSH1", "PUSH3", "PUSH2", "PUSH0", "PUSH3", "PUSH4"}; // ==> return ["ERROR", "OVERFLOW", "UNKNOWN"]

        // 각각의 스택 구현에 대해 테스트 실행
        runTest("Deque Test", MyDeque::new, input);
        System.out.println("====================================");
        runTest("List Test", MyList::new, input);
    }

    /**
     * @param testName 테스트 이름 출력용
     * @param stackSupplier 스택 인스턴스를 생성하는 Supplier (예: MyDeque::new, MyList::new)
     * @param input 실행할 커맨드 배열
     * @param <S> MyStack<Integer>을 구현한 타입
     */
    private static <S extends MyStack<Integer>> void runTest(String testName, Supplier<S> stackSupplier, String[] input) {
        System.out.println(testName);
        List<String> answer = new ArrayList<>();
        S stack = stackSupplier.get();
        StackCalculator calculator = new StackCalculator(stack);

        for (String command : input) {
            String ret = calculator.run(command);
            if (!ret.isEmpty()) {
                answer.add(ret);
            }
        }

        System.out.println(answer);
    }
}
