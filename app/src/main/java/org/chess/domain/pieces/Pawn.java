package org.chess.domain.pieces;

public class Pawn {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final String WHITE_REPRESENTATION = "p";
    public static final String BLACK_REPRESENTATION = "P";

    private final String color;

    public Pawn() {
        this.color = WHITE_COLOR;
    }

    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
