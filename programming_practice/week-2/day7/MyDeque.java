import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class MyDeque<T> implements MyStack<T> {

    private final Deque<T> deque;

    public MyDeque() {
        deque = new LinkedBlockingDeque<>();
    }

    @Override
    public void push(T value) {
        deque.push(value);
    }

    @Override
    public T pop() {
        return deque.pop();
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public int size() {
        return deque.size();
    }

}
