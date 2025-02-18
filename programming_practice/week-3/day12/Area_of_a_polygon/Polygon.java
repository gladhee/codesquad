import java.util.List;

public class Polygon implements Diagram {

    List<Point> points;

    public Polygon(List<Point> points) {
        this.points = points;
    }

    private double area() {
        double area = 0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            Point current = points.get(i);
            Point next = points.get((i + 1) % n);

            area += current.x() * next.y() - current.y() * next.x();
        }

        return Math.abs(area / 2);
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Polygon Area: ");
        sb.append(area());

        return sb.toString();
    }

}
