import java.util.Map;
import java.util.function.Supplier;

public class StackCalculator {

    private static final String EMPTY = "EMPTY";
    private static final String ERROR = "ERROR";
    private static final String OVERFLOW = "OVERFLOW";
    private static final String UNKNOWN = "UNKNOWN";
    private static final String NONE = "";

    private int registerA;
    private int registerB;
    private final MyStack<Integer> stack;
    private final Map<Command, Supplier<String>> commands;

    public StackCalculator(MyStack<Integer> stack) {
        registerA = -1;
        registerB = -1;
        this.stack = stack;
        commands = initCommands();
    }

    private Map<Command, Supplier<String>> initCommands() {
        return Map.of(
                Command.POPA, this::popA,
                Command.POPB, this::popB,
                Command.ADD, this::add,
                Command.SUB, this::sub,
                Command.PUSH0, this::push0,
                Command.PUSH1, this::push1,
                Command.PUSH2, this::push2,
                Command.PUSH3, this::push3,
                Command.SWAP, this::swap,
                Command.PRINT, this::print
        );
    }

    public String run(String commandStr) {
        return Command.from(commandStr)
                .map(commands::get)
                .map(Supplier::get)
                .orElse(UNKNOWN);
    }

    private String popA() {
        if (stack.isEmpty()) {
            return EMPTY;
        }
        registerA = stack.pop();
        return NONE;
    }

    private String popB() {
        if (stack.isEmpty()) {
            return EMPTY;
        }
        registerB = stack.pop();
        return NONE;
    }

    private String add() {
        if (isUnusable()) {
            return ERROR;
        }
        stack.push(registerA + registerB);
        return NONE;
    }

    private String sub() {
        if (isUnusable()) {
            return ERROR;
        }
        stack.push(registerA - registerB);
        return NONE;
    }

    private String push0() {
        if (isOverflow()) {
            return OVERFLOW;
        }
        stack.push(0);
        return NONE;
    }

    private String push1() {
        if (isOverflow()) {
            return OVERFLOW;
        }
        stack.push(1);
        return NONE;
    }

    private String push2() {
        if (isOverflow()) {
            return OVERFLOW;
        }
        stack.push(2);
        return NONE;
    }

    private String push3() {
        if (isOverflow()) {
            return OVERFLOW;
        }
        stack.push(3);
        return NONE;
    }

    private String swap() {
        if (isUnusable()) {
            return ERROR;
        }
        int temp = registerA;
        registerA = registerB;
        registerB = temp;
        return NONE;
    }

    private String print() {
        if (stack.isEmpty()) {
            return EMPTY;
        }
        return stack.pop().toString();
    }

    private boolean isOverflow() {
        return stack.size() >= 8;
    }

    private boolean isUnusable() {
        return registerA == -1 || registerB == -1;
    }

}
