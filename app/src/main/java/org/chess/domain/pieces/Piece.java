package org.chess.domain.pieces;

import java.util.Objects;

public class Piece {

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        NO_PIECE('.', 0.0);

        private final char representation;
        private final double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getType() {
            return representation;
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }

        public char getBlackType() {
            return Character.toUpperCase(representation);
        }

        public char getWhiteType() {
            return Character.toLowerCase(representation);
        }
    }

    private final Color color;
    private final Type representation;

    private Piece(Color color, Type representation) {
        this.color = color;
        this.representation = representation;
    }

    public static Piece createWhite(Piece.Type type) {
        return new Piece(Color.WHITE, type);
    }

    public static Piece createBlack(Piece.Type type) {
        return new Piece(Color.BLACK, type);
    }

    public static Piece createBlankPiece() {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isSameType(Type type) {
        return this.representation.equals(type);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return representation;
    }

    public double getPoint() {
        return representation.defaultPoint;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piece piece = (Piece) obj;
        return color.equals(piece.color) && representation == piece.representation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation);
    }

}
