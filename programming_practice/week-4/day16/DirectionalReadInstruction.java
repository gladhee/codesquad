import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectionalReadInstruction {

    private DirectionalReadInstruction() {
    }

    public static void readStart(int startX, int startY, List<Point> outPoints) {
        outPoints.add(new Point(startX, startY));
        outPoints.add(new Point(startX - 1, startY));
        outPoints.add(new Point(startX, startY - 1));
        outPoints.add(new Point(startX - 1, startY - 1));
    }

    public static void readEnd(int startX, int startY, List<Point> outPoints) {
        outPoints.add(new Point(startX, startY));
        outPoints.add(new Point(startX - 1, startY));
        outPoints.add(new Point(startX, startY + 1));
        outPoints.add(new Point(startX - 1, startY + 1));
    }

    public static void readUp(int startX, int startY, List<Point> outPoints) {
        readStart(startX, startY, outPoints);
        outPoints.add(new Point(startX, startY - 2));
        outPoints.add(new Point(startX - 1, startY - 2));
        outPoints.add(new Point(startX, startY - 3));
        outPoints.add(new Point(startX - 1, startY - 3));
    }

    public static void readDown(int startX, int startY, List<Point> outPoints) {
        readEnd(startX, startY, outPoints);
        outPoints.add(new Point(startX, startY + 2));
        outPoints.add(new Point(startX - 1, startY + 2));
        outPoints.add(new Point(startX, startY + 3));
        outPoints.add(new Point(startX - 1, startY + 3));
    }

    public static void readUpLeft(int startX, int startY, List<Point> outPoints) {
        readStart(startX, startY, outPoints);
        outPoints.add(new Point(startX - 2, startY - 1));
        outPoints.add(new Point(startX - 3, startY - 1));
        outPoints.add(new Point(startX - 2, startY));
        outPoints.add(new Point(startX - 3, startY));
    }

    public static void readDownLeft(int startX, int startY, List<Point> outPoints) {
        readEnd(startX, startY, outPoints);
        outPoints.add(new Point(startX - 2, startY + 1));
        outPoints.add(new Point(startX - 3, startY + 1));
        outPoints.add(new Point(startX - 2, startY));
        outPoints.add(new Point(startX - 3, startY));
    }

}
