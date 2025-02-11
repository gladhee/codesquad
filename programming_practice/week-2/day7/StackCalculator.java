public class StackCalculator {

    private static final String EMPTY = "EMPTY";
    private static final String ERROR = "ERROR";
    private static final String OVERFLOW = "OVERFLOW";
    private static final String UNKNOWN = "UNKNOWN";
    private static final String NONE = "";

    private int registerA;
    private int registerB;
    private final MyStack<Integer> stack;

    public StackCalculator(MyStack<Integer> stack) {
        registerA = -1;
        registerB = -1;
        this.stack = stack;
    }

    public String run(String command) {
        if (command.equalsIgnoreCase("POPA")) {
            return popA();
        } else if (command.equalsIgnoreCase("POPB")) {
            return popB();
        } else if (command.equalsIgnoreCase("ADD")) {
            return add();
        } else if (command.equalsIgnoreCase("SUB")) {
            return sub();
        } else if (command.equalsIgnoreCase("PUSH0")) {
            return push0();
        } else if (command.equalsIgnoreCase("PUSH1")) {
            return push1();
        } else if (command.equalsIgnoreCase("PUSH2")) {
            return push2();
        } else if (command.equalsIgnoreCase("PUSH3")) {
            return push3();
        } else if (command.equalsIgnoreCase("SWAP")) {
            return swap();
        } else if (command.equalsIgnoreCase("PRINT")) {
            return print();
        } else {
            return UNKNOWN;
        }
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
