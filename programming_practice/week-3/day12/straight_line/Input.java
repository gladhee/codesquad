import java.util.List;
import java.util.Scanner;

public class Input {

    private final Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public List<Point> getPoints() {
        System.out.println("Enter the coordinates of the point: Usage: \"(x1,y1)-(x2,y2)\"");

        while (true) {
            try {
                String[] points = scanner.nextLine().split("-");
                if (points.length != 2) {
                    throw new IllegalArgumentException("Invalid input. Please enter two points.");
                }

                Point p1 = parsePoint(points[0]);
                Point p2 = parsePoint(points[1]);

                return List.of(p1, p2);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Point parsePoint(String point) {
        String removedParentheses = point.substring(1, point.length() - 1);
        String[] coordinates = removedParentheses.split(",");
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("Invalid input. Please enter two coordinates.");
        }

        try {
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);

            return new Point(x, y);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter valid coordinates.");
        }
    }

}
