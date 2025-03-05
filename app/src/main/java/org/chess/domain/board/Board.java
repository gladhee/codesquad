package org.chess.domain.board;

import org.chess.domain.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int BOARD_SIZE = 8;
    private static final int WHITE_PAWN_ROW = 6;
    private static final int BLACK_PAWN_ROW = 1;
    private static final char EMPTY = '.';

    private final char[][] board;
    private final List<Pawn> pawns;

    public Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.pawns = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 0; i < 8; i++) {
            pawns.add(new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION));
            pawns.add(new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION));
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
            board[BLACK_PAWN_ROW][i] = pawns.get(1).getRepresentation();
            board[WHITE_PAWN_ROW][i] = pawns.get(0).getRepresentation();
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

    public void add(Pawn pawn) {
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int index) {
        return pawns.get(index);
    }

    public String getPawnsResultWith(int row) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            sb.append(board[row][i]);
        }
        return sb.toString();
    }

}
