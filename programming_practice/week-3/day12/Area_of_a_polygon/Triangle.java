import java.util.List;

public class Triangle implements Diagram {

    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private double area() {
        return Math.abs((a.x() * (b.y() - c.y()) + b.x() * (c.y() - a.y()) + c.x() * (a.y() - b.y())) / 2);
    }

    @Override
    public List<Point> getPoints() {
        return List.of(a, b, c);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Triangle Area: ");
        sb.append(area());

        return sb.toString();
    }

}
