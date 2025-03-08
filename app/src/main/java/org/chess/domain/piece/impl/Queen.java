package org.chess.domain.piece.impl;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;

public class Queen implements Piece {

    private final Color color;

    public Queen(Color color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        return false;
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "q" : "Q";
    }

}
