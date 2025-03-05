package org.chess.domain.board;

import org.chess.domain.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int BOARD_SIZE = 8;
    private static final int WHITE_PAWN_ROW = 6;
    private static final int BLACK_PAWN_ROW = 1;
    private static final char EMPTY = '.';

    private final char[][] board;
    private final List<Piece> pieces;

    public Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.pieces = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 0; i < 8; i++) {
            pieces.add(new Piece(Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION));
            pieces.add(new Piece(Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION));
        }

        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }

        for (int i = 0; i < 8; i++) {
            board[BLACK_PAWN_ROW][i] = pieces.get(1).getRepresentation();
            board[WHITE_PAWN_ROW][i] = pieces.get(0).getRepresentation();
        }
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public Piece findPawn(int index) {
        return pieces.get(index);
    }

    public String getPawnsResultWith(int row) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            sb.append(board[row][i]);
        }
        return sb.toString();
    }

}
