package org.chess.domain.game;

import org.chess.domain.board.Board;
import org.chess.domain.board.Position;
import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;
import org.chess.domain.piece.PieceFactory;
import org.chess.domain.piece.impl.Blank;
import org.chess.domain.piece.provider.TestBoardProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameTest {

    @ParameterizedTest(name = "[{index}] White Pawn 이동: {0} → {1}")
    @CsvSource({
            "a2, a3",
            "b2, b3"
    })
    @DisplayName("White Pawn이 현재 턴일 때 유효한 이동이 주어지면, 기물이 이동하고 출발 자리는 Blank가 되어야 한다")
    void 정상_이동_테스트(String fromStr, String toStr) {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of(fromStr);
        Position to = Position.of(toStr);
        Piece whitePawn = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, whitePawn);
        Game game = Game.newGame(board);

        // when
        game.move(from, to);

        // then
        assertThat(board.getPiece(to).belongsTo(Color.WHITE)).isTrue();
        assertThat(board.getPiece(from)).isInstanceOf(Blank.class);
    }

    @Test
    @DisplayName("현재 턴과 다른 색의 기물을 이동하려 하면, When move() 호출 시, 예외가 발생해야 한다")
    void 현재_턴과_다른_색의_기물_이동_예외_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of("a2");
        Position to = Position.of("a3");
        Piece blackPawn = PieceFactory.PAWN.create(Color.BLACK);
        TestBoardProvider.setPiece(board, from, blackPawn);
        Game game = Game.newGame(board);

        // when & then
        assertThatThrownBy(() -> game.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 규칙에 어긋난 이동이 주어지면 예외가 발생해야 한다")
    void 어긋난_이동_예외_테스트() {
        // given
        Board board = TestBoardProvider.createEmptyBoard();
        Position from = Position.of("a2");
        Position to = Position.of("b2");
        Piece whitePawn = PieceFactory.PAWN.create(Color.WHITE);
        TestBoardProvider.setPiece(board, from, whitePawn);
        Game game = Game.newGame(board);

        // when & then
        assertThatThrownBy(() -> game.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동할 수 없습니다");
    }

}
