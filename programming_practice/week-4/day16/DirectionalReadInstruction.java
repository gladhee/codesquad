import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectionalReadInstruction {

    private DirectionalReadInstruction() {
    }

    public static Queue<MoveReader> getMoveReaders() {
        Queue<MoveReader> queue = new LinkedList<>();

        queue.add((out -> readStart(20, 20, out)));
        queue.add(out -> readUp(20, 18, out));
        queue.add(out -> readUp(20, 14, out));
        queue.add(out -> readUpLeft(20, 10, out));
        queue.add(out -> readDown(18, 11, out));
        queue.add(out -> readDown(18, 15, out));
        queue.add(out -> readDownLeft(18, 19, out));
        queue.add(out -> readUp(16, 18, out));
        queue.add(out -> readUp(16, 14, out));
        queue.add(out -> readUpLeft(16, 10, out));
        queue.add(out -> readDown(14, 11, out));
        queue.add(out -> readDown(14, 15, out));
        queue.add(out -> readDownLeft(14, 19, out));
        queue.add(out -> readUp(12, 18, out));
        queue.add(out -> readUp(12, 14, out));
        queue.add(out -> readUpLeft(12, 10, out));
        queue.add(out -> readUp(12, 5, out));
        queue.add(out -> readUpLeft(12, 1, out));
        queue.add(out -> readDown(10, 2, out));
        queue.add(out -> readDown(10, 7, out));
        queue.add(out -> readDown(10, 11, out));
        queue.add(out -> readDown(10, 15, out));
        queue.add(out -> readEnd(10, 19, out));
        queue.add(out -> readUp(8, 12, out));
        queue.add(out -> readDown(5, 9, out));
        queue.add(out -> readUp(3, 12, out));
        queue.add(out -> readDown(1, 9, out));

        return queue;
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
