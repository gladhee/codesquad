import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class QRDecode {

    private final char[][] qrCode;
    private final Queue<MoveReader> moveReaders;

    private QRDecode(char[][] qrCode, Queue<MoveReader> moveReaders) {
        this.qrCode = qrCode;
        this.moveReaders = moveReaders;
    }

    public static QRDecode of(char[][] qrCode, Queue<MoveReader> moveReaders) {
        return new QRDecode(qrCode, moveReaders);
    }

    public List<Integer> decode() {
        List<Integer> decoded = new ArrayList<>();

        while (!moveReaders.isEmpty()) {
            MoveReader reader = moveReaders.poll();

            List<Point> order = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            reader.readData(order);

            for (Point p : order) {
                char data = qrCode[p.y()][p.x()];
                sb.append(data);
            }

            int value = Integer.parseInt(sb.toString(), 2);
            decoded.add(value);
        }

        return decoded;
    }

}
