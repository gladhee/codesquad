package org.chess.domain.board;

import java.util.Objects;

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

        // 8 -> 0, 7 -> 1, ..., 1 -> 7
        int y = BOARD_SIZE - pos.charAt(1) + '0';
        return new Position(y, x);
    }

    public static Position of(int y, int x) {
        return new Position(y, x);
    }

    public int y() {
        return y;
    }

    public int x() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return y == position.y && x == position.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

}
