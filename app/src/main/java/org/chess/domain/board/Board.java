package org.chess.domain.board;

import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;
import org.chess.domain.piece.PieceFactory;
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
            board.put(Position.of(6, i), PieceFactory.PAWN.create(Color.WHITE));
            board.put(Position.of(1, i), PieceFactory.PAWN.create(Color.BLACK));
        }
        // 기타 기물 배치 (룩, 나이트, 비숍, 킹, 퀸)
        board.put(Position.of(7, 0), PieceFactory.ROOK.create(Color.WHITE));
        board.put(Position.of(7, 7), PieceFactory.ROOK.create(Color.WHITE));
        board.put(Position.of(0, 0), PieceFactory.ROOK.create(Color.BLACK));
        board.put(Position.of(0, 7), PieceFactory.ROOK.create(Color.BLACK));

        board.put(Position.of(7, 1), PieceFactory.KNIGHT.create(Color.WHITE));
        board.put(Position.of(7, 6), PieceFactory.KNIGHT.create(Color.WHITE));
        board.put(Position.of(0, 1), PieceFactory.KNIGHT.create(Color.BLACK));
        board.put(Position.of(0, 6), PieceFactory.KNIGHT.create(Color.BLACK));

        board.put(Position.of(7, 2), PieceFactory.BISHOP.create(Color.WHITE));
        board.put(Position.of(7, 5), PieceFactory.BISHOP.create(Color.WHITE));
        board.put(Position.of(0, 2), PieceFactory.BISHOP.create(Color.BLACK));
        board.put(Position.of(0, 5), PieceFactory.BISHOP.create(Color.BLACK));

        board.put(Position.of(7, 3), PieceFactory.QUEEN.create(Color.WHITE));
        board.put(Position.of(0, 3), PieceFactory.QUEEN.create(Color.BLACK));

        board.put(Position.of(7, 4), PieceFactory.KING.create(Color.WHITE));
        board.put(Position.of(0, 4), PieceFactory.KING.create(Color.BLACK));
    }

    private void setEmptyBoard() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                board.put(Position.of(y, x), PieceFactory.BLANK.create(Color.NOCOLOR));
            }
        }
    }

    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        board.remove(from);
        board.put(to, piece);
        board.put(from, PieceFactory.BLANK.create(Color.NOCOLOR));
    }

    public Piece getPiece(Position pos) {
        return board.getOrDefault(pos, PieceFactory.BLANK.create(Color.NOCOLOR));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                Piece piece = board.get(Position.of(y, x));
                sb.append(piece);
            }
            sb.append(StringUtils.NEWLINE);
        }
        return sb.toString();
    }

}
