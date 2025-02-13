import java.util.ArrayList;
import java.util.List;

public class Desk {

    private static final int MAX_WIDTH = 10;

    private final List<Parts> components;
    private final int number;

    public Desk(int number) {
        this.components = new ArrayList<>();
        this.number = number;
    }

    public void push(Parts component) {
        this.components.add(component);
    }

    public Parts pop() {
        if (this.components.isEmpty()) {
            return null;
        }

        return this.components.removeLast();
    }

    public List<String> toList() {
        List<String> lines = new ArrayList<>();
        for (Parts component : this.components) {
            String[] parts = component.toString().split("\n");
            for (String part : parts) {
                lines.addFirst(part);
            }
        }

        for (int i = lines.size(); i < MAX_WIDTH - 1; ++i) {
            lines.addFirst("         ");
        }
        lines.addLast(number + "========");

        return lines;
    }

}
