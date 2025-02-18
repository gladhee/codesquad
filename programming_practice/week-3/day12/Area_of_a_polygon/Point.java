public record Point(int x, int y) {

    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be positive");
        }
        if (x > 24 || y > 24) {
            throw new IllegalArgumentException("Coordinates must be less than 25");
        }
    }

}
