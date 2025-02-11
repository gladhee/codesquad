import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyStack<T> {

    private final List<T> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    @Override
    public void push(T value) {
        list.addFirst(value);
    }

    @Override
    public T pop() {
        return list.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

}
