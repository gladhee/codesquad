package org.chess.domain.board;

import org.chess.domain.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final List<Piece> pieces;

    private Rank(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Rank of(List<Piece> pieces) {
        return new Rank(pieces);
    }

    public void placePieceAt(int index, Piece piece) {
        validateIndex(index);

        pieces.set(index, piece);
    }

    public Piece getPieceAt(int index) {
        validateIndex(index);

        return pieces.get(index);
    }

    public double calculatePoints(Piece.Color color) {
        return pieces.stream()
                .filter(piece -> piece.getColor() == color)
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    public int countPieces(Piece.Color color, Piece.Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.getColor() == color)
                .filter(piece -> piece.getType() == type)
                .count();
    }

    public List<Piece> getPiecesByColor(Piece.Color color) {
        List<Piece> result = new ArrayList<>();
        for (Piece piece : pieces) {
            if (piece.getColor() == color) {
                result.add(piece);
            }
        }
        return result;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= pieces.size()) {
            throw new IllegalArgumentException("Index out of range");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : pieces) {
            sb.append(piece.getColor() == Piece.Color.WHITE ? piece.getType().getWhiteType() : piece.getType().getBlackType());
        }
        return sb.toString();
    }

}
