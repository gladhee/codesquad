package org.chess.domain.pieces;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("팩토리 메소드에 의해 맞는 색상과 표현을 가진 Piece를 생성할 수 있어야 한다")
    void Piece_생성_테스트() {
        verifyPiece(Piece.createWhitePawn(), Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPiece(Piece.createBlackPawn(), Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);
        verifyPiece(Piece.createWhiteQuenn(), Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createBlackQuenn(), Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createWhiteKing(), Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
        verifyPiece(Piece.createBlackKing(), Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);
        verifyPiece(Piece.createWhiteRook(), Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION);
        verifyPiece(Piece.createBlackRook(), Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION);
        verifyPiece(Piece.createWhiteBishop(), Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createBlackBishop(), Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createWhiteKnight(), Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createBlackKnight(), Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);
    }

    private void verifyPiece(final Piece piece, final String color, final char representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

    @ParameterizedTest
    @MethodSource("whitePiecesProvider")
    @DisplayName("White 로 생성한 Piece의 색상은 White이어야 한다")
    void 흰색_기물_생성시_색상_검증_테스트(Piece piece) {
        assertThat(piece.isWhite()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("blackPiecesProvider")
    @DisplayName("Black으로 생성한 Piece의 색상은 Black이어야 한다")
    void 검은색_기물_생성시_색상_검증_테스트(Piece piece) {
        assertThat(piece.isBlack()).isTrue();
    }

    static Stream<Piece> whitePiecesProvider() {
        return Stream.of(
                Piece.createWhitePawn(),
                Piece.createWhiteQuenn(),
                Piece.createWhiteKing(),
                Piece.createWhiteRook(),
                Piece.createWhiteBishop(),
                Piece.createWhiteKnight()
        );
    }

    static Stream<Piece> blackPiecesProvider() {
        return Stream.of(
                Piece.createBlackPawn(),
                Piece.createBlackQuenn(),
                Piece.createBlackKing(),
                Piece.createBlackRook(),
                Piece.createBlackBishop(),
                Piece.createBlackKnight()
        );
    }

}
