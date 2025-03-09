package org.chess.domain.board;

import org.chess.domain.piece.Color;
import org.chess.domain.piece.PieceFactory;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.chess.utils.StringUtils.appendNewLine;

class BoardTest {

    @Test
    @DisplayName("보드 initailize 후 모든 기물이 생성되어야 한다")
    void initializeAllPieces() {
        // given & when
        Board board = Board.create();

        // then
        String blankRank = appendNewLine("........");

        assertThat(appendNewLine("RNBQKBNR") +
                appendNewLine("PPPPPPPP") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("pppppppp") +
                appendNewLine("rnbqkbnr"))
                .isEqualTo(board.toString());
    }

    @Test
    @DisplayName("임의의 주어진 위치의 기물을 조회할 수 있어야 한다")
    void 기물_조회_테스트() {
        // given & when
        Board board = Board.create();

        // then
        assertThat(PieceFactory.ROOK.create(Color.BLACK))
                .isEqualTo(board.getPiece(Position.of("h8")));
        assertThat(PieceFactory.ROOK.create(Color.WHITE))
                .isEqualTo(board.getPiece(Position.of("h1")));
        assertThat(PieceFactory.ROOK.create(Color.BLACK))
                .isEqualTo(board.getPiece(Position.of("a8")));
        assertThat(PieceFactory.ROOK.create(Color.WHITE))
                .isEqualTo(board.getPiece(Position.of("a1")));
    }

    @Test
    @DisplayName("from 에서 to 로 기물을 이동할 수 있어야 한다")
    void 기물_이동_테스트() {
        // given
        Board board = Board.create();

        // when
        Position srcPos = Position.of("b2");
        Position destPos = Position.of("b4");
        board.movePiece(srcPos, destPos);

        assertThat(PieceFactory.PAWN.create(Color.WHITE)).isEqualTo(board.getPiece(destPos));
        assertThat(PieceFactory.BLANK.create(Color.NOCOLOR)).isEqualTo(board.getPiece(srcPos));
    }

}
