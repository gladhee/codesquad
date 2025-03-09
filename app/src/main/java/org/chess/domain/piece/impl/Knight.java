package org.chess.domain.piece.impl;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;

public class Knight implements Piece {

    private final Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Knight knight = (Knight) obj;
        return color == knight.color;
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "n" : "N";
    }

}
