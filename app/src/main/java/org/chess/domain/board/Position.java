package org.chess.domain.board;

public class Position {

    private static final int BOARD_SIZE = 8;

    private final int y;
    private final int x;

    private Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public static Position of(String pos) {
        int x = pos.charAt(0) - 'a';
        int y = pos.charAt(1) - '1';
        return new Position(y, x);
    }

    public int y() {
        return BOARD_SIZE - y - 1;
    }

    public int x() {
        return x;
    }

}
