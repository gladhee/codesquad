import java.util.Arrays;
import java.util.Optional;

public enum Command {

    POPA("POPA"),
    POPB("POPB"),
    ADD("ADD"),
    SUB("SUB"),
    PUSH0("PUSH0"),
    PUSH1("PUSH1"),
    PUSH2("PUSH2"),
    PUSH3("PUSH3"),
    SWAP("SWAP"),
    PRINT("PRINT");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Optional<Command> from(String commandStr) {
        return Arrays.stream(values())
                .filter(c -> c.getCommand().equalsIgnoreCase(commandStr))
                .findFirst();
    }

}
