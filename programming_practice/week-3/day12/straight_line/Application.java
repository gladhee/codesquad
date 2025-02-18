import java.util.List;

public class Application {

    public static void main(String[] args) {
        Input input = new Input();
        List<Point> points = input.getPoints();

        Plane plane = new Plane(points.get(0), points.get(1));

        System.out.println(plane);
        System.out.println("Distance between points: " + plane.distance());
    }

}
