package org.chess.domain.game;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;

public class Game {
    private final Board board;
    private Color currentTurn;

    private Game(Board board) {
        this.board = board;
        currentTurn = Color.WHITE;
    }

    public static Game newGame(Board board) {
        return new Game(board);
    }

    /**
     * @param from 출발 위치 (예: "a2")
     * @param to   도착 위치 (예: "a3")
     * @throws IllegalArgumentException 이동하려는 기물이 현재 턴의 기물이 아닐 때 및 이동이 불가능할 때
     * @brief 주어진 from, to 체스 표기법 문자열에 따라 기물을 이동시킨다.
     */
    public void move(Position from, Position to) {
        if (!board.getPiece(from).belongsTo(currentTurn)) {
            throw new IllegalArgumentException("현재 턴(" + currentTurn + ")의 기물이 아닙니다.");
        }

        // 이동이 유효한지 Board에서 판단 (유효하지 않으면 내부에서 예외 발생)
        board.movePiece(from, to);

        // 턴 토글
        currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

}
