public class Plane {

    private static final int MAX_COORD = 24;
    private static final int HEIGHT = MAX_COORD + 2;
    private static final int WIDTH  = (2 * (MAX_COORD + 1)) + 3;
    private static final int X_OFFSET = 3;
    private static final int Y_OFFSET = 1;
    private static final char EMPTY = 32;
    private static final char VERTICAL = 124;
    private static final char HORIZONTAL = 45;
    private static final char INTERSECTION = 43;
    private static final char ZERO = 48;
    private static final char POINT = 9679;

    private final Point p1;
    private final Point p2;

    public Plane(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double distance() {
        return Math.sqrt(Math.pow(p2.x() - p1.x(), 2) + Math.pow(p2.y() - p1.y(), 2));
    }

    @Override
    public String toString() {
        char[][] plane = new char[HEIGHT][WIDTH];
        for (int r = 0; r < HEIGHT; r++) {
            for (int c = 0; c < WIDTH; c++) {
                plane[r][c] = EMPTY;
            }
        }

        for (int row = 1; row <= MAX_COORD + 1; row++) {
            int yVal = row - 1;

            if ((yVal & 1) == 0) {
                if (yVal < 10) {
                    plane[row][0] = EMPTY;
                    plane[row][1] = (char) (ZERO + yVal);
                } else {
                    plane[row][0] = (char) (ZERO + (yVal / 10));
                    plane[row][1] = (char) (ZERO + (yVal % 10));
                }
            }

            plane[row][2] = EMPTY;

            plane[row][X_OFFSET] = VERTICAL;
        }

        //    col=3..51 => x = (col-3)/2
        //    row=1 => 실제 y=0
        for (int x = 0; x <= MAX_COORD; x++) {
            int col = X_OFFSET + 2 * x;
            if ((x & 1) == 0) {
                String xStr = String.valueOf(x);
                plane[0][col] = xStr.charAt(0);
                if (x >= 10) {
                    plane[0][col + 1] = xStr.charAt(1);
                }
            }

            plane[Y_OFFSET][col] = HORIZONTAL;
        }
        plane[Y_OFFSET][X_OFFSET] = INTERSECTION;

        drawPerpendicular(plane, p1);
        drawPerpendicular(plane, p2);

        int rowP1 = p1.y() + Y_OFFSET;
        int colP1 = X_OFFSET + 2 * p1.x();
        plane[rowP1][colP1] = POINT;

        int rowP2 = p2.y() + Y_OFFSET;
        int colP2 = X_OFFSET + 2*p2.x();
        plane[rowP2][colP2] = POINT;

        StringBuilder sb = new StringBuilder();

        for (int r = HEIGHT - 1; r >= 0; r--) {
            for (int c = 0; c < WIDTH; c++) {
                sb.append(plane[r][c]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private void drawPerpendicular(char[][] plane, Point p) {
        int rowP = p.y() + Y_OFFSET;
        int colP = X_OFFSET + 2 * p.x();

        for (int r = 1; r <= rowP; r++) {
            plane[r][colP] = VERTICAL;
        }
        for (int c = X_OFFSET; c <= colP; c++) {
            plane[rowP][c] = HORIZONTAL;
        }
        plane[Y_OFFSET][X_OFFSET] = INTERSECTION;
    }

}
