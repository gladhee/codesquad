import java.util.List;

public record Straight(Point a, Point b) implements Diagram {

    private double distance() {
        return Math.sqrt(Math.pow(b.x() - a.x(), 2) + Math.pow(b.y() - a.y(), 2));
    }

    @Override
    public List<Point> getPoints() {
        return List.of(a, b);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Straight Distance: ");
        sb.append(distance());

        return sb.toString();
    }

}
