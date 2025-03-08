package org.chess.domain.board;

import org.chess.domain.piece.Color;
import org.chess.domain.piece.Piece;
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
    @DisplayName("Piece 의 색과 종류를 반환할 수 있어야 한다")
    void 기물의_개수_반환_테스트() {
        // given & when
        Board board = Board.create();

        // then
        assertThat(8).isEqualTo(board.countPieces(Color.WHITE, Piece.Type.PAWN));
        assertThat(2).isEqualTo(board.countPieces(Color.WHITE, Piece.Type.ROOK));
        assertThat(2).isEqualTo(board.countPieces(Color.WHITE, Piece.Type.KNIGHT));
    }

    @Test
    @DisplayName("임의의 주어진 위치의 기물을 조회할 수 있어야 한다")
    void 기물_조회_테스트() {
        // given & when
        Board board = Board.create();

        // then
        assertThat(Piece.createBlack(Piece.Type.ROOK))
                .isEqualTo(board.getPiece(Position.of("h8")));
        assertThat(Piece.createWhite(Piece.Type.ROOK))
                .isEqualTo(board.getPiece(Position.of("h1")));
        assertThat(Piece.createBlack(Piece.Type.ROOK))
                .isEqualTo(board.getPiece(Position.of("a8")));
        assertThat(Piece.createWhite(Piece.Type.ROOK))
                .isEqualTo(board.getPiece(Position.of("a1")));
    }

    @Test
    @DisplayName("현재까지 남아있는 기물에 따라 점수를 계산할 수 있어야 한다")
    void 점수_계산_테스트() {
        // given & when
        Board board = Board.create();

        // then
        assertThat(board.calculatePoint(Color.BLACK)).isCloseTo(38.0, within(0.01));
        assertThat(board.calculatePoint(Color.WHITE)).isCloseTo(38.0, within(0.01));
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

        assertThat(Piece.createWhite(Piece.Type.PAWN)).isEqualTo(board.getPiece(destPos));
        assertThat(Piece.createBlankPiece()).isEqualTo(board.getPiece(srcPos));
    }

}
