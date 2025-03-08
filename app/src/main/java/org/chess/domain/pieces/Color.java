package org.chess.domain.pieces;

public enum Color {

    WHITE("white"),
    BLACK("black"),
    NOCOLOR("null");

    private final String color;

    Color(String color) {
        this.color = color;
    }

}
