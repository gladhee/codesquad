import java.io.IOException;

@FunctionalInterface
public interface Command<T> {
    void execute(T t) throws IOException;
}
