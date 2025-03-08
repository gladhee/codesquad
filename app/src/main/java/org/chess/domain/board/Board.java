package org.chess.domain.board;

import org.chess.domain.pieces.Piece;
import org.chess.utils.StringUtils;

import java.util.*;

public class Board {

    private static final int BOARD_SIZE = 8;
    private final Map<Position, Piece> board;

    private Board() {
        this.board = new HashMap<>();
        initialize();
    }

    public static Board create() {
        return new Board();
    }

    private void initialize() {
        setEmptyBoard();
        for (int i = 0; i < BOARD_SIZE; i++) {
            board.put(Position.of(6, i), Piece.createWhite(Piece.Type.PAWN));
            board.put(Position.of(1, i), Piece.createBlack(Piece.Type.PAWN));
        }
        // 기타 기물 배치 (룩, 나이트, 비숍, 킹, 퀸)
        board.put(Position.of(7, 0), Piece.createWhite(Piece.Type.ROOK));
        board.put(Position.of(7, 7), Piece.createWhite(Piece.Type.ROOK));
        board.put(Position.of(0, 0), Piece.createBlack(Piece.Type.ROOK));
        board.put(Position.of(0, 7), Piece.createBlack(Piece.Type.ROOK));

        board.put(Position.of(7, 1), Piece.createWhite(Piece.Type.KNIGHT));
        board.put(Position.of(7, 6), Piece.createWhite(Piece.Type.KNIGHT));
        board.put(Position.of(0, 1), Piece.createBlack(Piece.Type.KNIGHT));
        board.put(Position.of(0, 6), Piece.createBlack(Piece.Type.KNIGHT));

        board.put(Position.of(7, 2), Piece.createWhite(Piece.Type.BISHOP));
        board.put(Position.of(7, 5), Piece.createWhite(Piece.Type.BISHOP));
        board.put(Position.of(0, 2), Piece.createBlack(Piece.Type.BISHOP));
        board.put(Position.of(0, 5), Piece.createBlack(Piece.Type.BISHOP));

        board.put(Position.of(7, 3), Piece.createWhite(Piece.Type.QUEEN));
        board.put(Position.of(0, 3), Piece.createBlack(Piece.Type.QUEEN));

        board.put(Position.of(7, 4), Piece.createWhite(Piece.Type.KING));
        board.put(Position.of(0, 4), Piece.createBlack(Piece.Type.KING));
    }

    private void setEmptyBoard() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                board.put(Position.of(y, x), Piece.createBlankPiece());
            }
        }
    }

    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        board.remove(from);
        board.put(to, piece);
        board.put(from, Piece.createBlankPiece());
    }

    public Piece getPiece(Position pos) {
        return board.getOrDefault(pos, Piece.createBlankPiece());
    }

    public int countPieces(Piece.Color color, Piece.Type type) {
        return (int) board.values().stream()
                .filter(piece -> piece.isSameColor(color) && piece.isSameType(type))
                .count();
    }

    public double calculatePoint(Piece.Color color) {
        return board.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                Piece piece = board.get(Position.of(y, x));
                sb.append(piece.isSameColor(Piece.Color.WHITE) ? piece.getType().getWhiteType() : piece.getType().getBlackType());
            }
            sb.append(StringUtils.NEWLINE);
        }
        return sb.toString();
    }

}
