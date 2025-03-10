package org.chess.domain.game;

import org.chess.console.Input;
import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;

public class Game {

    private static final int FROM = 0;
    private static final int TO = 1;

    private final Board board;
    private final Input input;
    private Color currentTurn;

    private Game(Board board, Input input) {
        this.board = board;
        this.input = input;
        currentTurn = Color.WHITE;
    }

    public static Game newGame(Board board, Input input) {
        return new Game(board, input);
    }

    public void run() {
        System.out.println("게임을 시작합니다.");
        System.out.println(board);
        while (true) {
            try {
                String[] moves = input.getMoves();
                move(Position.of(moves[FROM]), Position.of(moves[TO]));
                System.out.println(board);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param from 출발 위치 (예: "a2")
     * @param to   도착 위치 (예: "a3")
     * @throws IllegalArgumentException 이동하려는 기물이 현재 턴의 기물이 아닐 때 및 이동이 불가능할 때
     * @brief 주어진 from, to 체스 표기법 문자열에 따라 기물을 이동시킨다.
     */
    public void move(Position from, Position to) {
        isMyTurn(from);
        board.movePiece(from, to);
        toggleTurn();
    }

    private void isMyTurn(Position from) {
        if (!board.getPiece(from).belongsTo(currentTurn)) {
            throw new IllegalArgumentException("현재 턴(" + currentTurn + ")의 기물이 아닙니다.");
        }
    }

    private void toggleTurn() {
        currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

}
