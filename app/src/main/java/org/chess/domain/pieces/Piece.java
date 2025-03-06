package org.chess.domain.pieces;

public class Piece {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    public static final String PAWN_NAME = "pawn";
    public static final String QUENN_NAME = "queen";
    public static final String KING_NAME = "king";
    public static final String ROOK_NAME = "rook";
    public static final String BISHOP_NAME = "bishop";
    public static final String KNIGHT_NAME = "knight";

    public static final char WHITE_PAWN_REPRESENTATION = 'p';
    public static final char BLACK_PAWN_REPRESENTATION = 'P';
    public static final char WHITE_QUEEN_REPRESENTATION = 'q';
    public static final char BLACK_QUEEN_REPRESENTATION = 'Q';
    public static final char WHITE_KING_REPRESENTATION = 'k';
    public static final char BLACK_KING_REPRESENTATION = 'K';
    public static final char WHITE_ROOK_REPRESENTATION = 'r';
    public static final char BLACK_ROOK_REPRESENTATION = 'R';
    public static final char WHITE_BISHOP_REPRESENTATION = 'b';
    public static final char BLACK_BISHOP_REPRESENTATION = 'B';
    public static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    public static final char BLACK_KNIGHT_REPRESENTATION = 'N';

    private final String name;
    private final String color;
    private final char representation;

    private Piece(String name, String color, char representation) {
        this.name = name;
        this.color = color;
        this.representation = representation;
    }

    public static Piece createWhitePawn() {
        return new Piece(PAWN_NAME, WHITE_COLOR, WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(PAWN_NAME, BLACK_COLOR, BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createWhiteQuenn() {
        return new Piece(QUENN_NAME, WHITE_COLOR, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQuenn() {
        return new Piece(QUENN_NAME, BLACK_COLOR, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return new Piece(KING_NAME, WHITE_COLOR, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(KING_NAME, BLACK_COLOR, BLACK_KING_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return new Piece(ROOK_NAME, WHITE_COLOR, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(ROOK_NAME, BLACK_COLOR, BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(BISHOP_NAME, WHITE_COLOR, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(BISHOP_NAME, BLACK_COLOR, BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(KNIGHT_NAME, WHITE_COLOR, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(KNIGHT_NAME, BLACK_COLOR, BLACK_KNIGHT_REPRESENTATION);
    }

    public boolean isWhite() {
        return color.equals(WHITE_COLOR);
    }

    public boolean isBlack() {
        return color.equals(BLACK_COLOR);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

}
