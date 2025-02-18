public class Application {

    public static void main(String[] args) {
        Point point1 = new Point(10, 10);
        Point point2 = new Point(14, 15);

        Plane plane = new Plane(point1, point2);

        plane.draw();
        System.out.println("Distance between points: " + plane.distance());
    }

}
