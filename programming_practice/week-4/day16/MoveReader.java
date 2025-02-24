import java.util.List;

@FunctionalInterface
public interface MoveReader {
    void readData(List<Point> out);
}
