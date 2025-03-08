package org.chess.domain.piece;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("팩토리 메소드에 의해 맞는 색상과 표현을 가진 Piece를 생성할 수 있어야 한다")
    void Piece_생성_테스트() {
        verifyPiece(Piece.createWhite(Piece.Type.PAWN), Piece.createBlack(Piece.Type.PAWN), Piece.Type.PAWN);
        verifyPiece(Piece.createWhite(Piece.Type.KNIGHT), Piece.createBlack(Piece.Type.KNIGHT), Piece.Type.KNIGHT);
        verifyPiece(Piece.createWhite(Piece.Type.ROOK), Piece.createBlack(Piece.Type.ROOK), Piece.Type.ROOK);
        verifyPiece(Piece.createWhite(Piece.Type.BISHOP), Piece.createBlack(Piece.Type.BISHOP), Piece.Type.BISHOP);
        verifyPiece(Piece.createWhite(Piece.Type.QUEEN), Piece.createBlack(Piece.Type.QUEEN), Piece.Type.QUEEN);
        verifyPiece(Piece.createWhite(Piece.Type.KING), Piece.createBlack(Piece.Type.KING), Piece.Type.KING);

        Piece blank = Piece.createBlankPiece();
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();
        assertThat(blank.getType()).isEqualTo(Piece.Type.NO_PIECE);
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Piece.Type type) {
        assertThat(whitePiece.isWhite()).isTrue();
        assertThat(whitePiece.getType()).isEqualTo(type);


        assertThat(blackPiece.isBlack()).isTrue();
        assertThat(blackPiece.getType()).isEqualTo(type);
    }

    @Test
    @DisplayName("Piece의 표현을 검은 색과 흰 색에 따라 반환해야 한다")
    void enum_Type_표현_반환_테스트() {
        assertThat('p').isEqualTo(Piece.Type.PAWN.getWhiteType());
        assertThat('P').isEqualTo(Piece.Type.PAWN.getBlackType());
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
                Piece.createWhite(Piece.Type.PAWN),
                Piece.createWhite(Piece.Type.QUEEN),
                Piece.createWhite(Piece.Type.KING),
                Piece.createWhite(Piece.Type.ROOK),
                Piece.createWhite(Piece.Type.BISHOP),
                Piece.createWhite(Piece.Type.KNIGHT)
        );
    }

    static Stream<Piece> blackPiecesProvider() {
        return Stream.of(
                Piece.createBlack(Piece.Type.PAWN),
                Piece.createBlack(Piece.Type.QUEEN),
                Piece.createBlack(Piece.Type.KING),
                Piece.createBlack(Piece.Type.ROOK),
                Piece.createBlack(Piece.Type.BISHOP),
                Piece.createBlack(Piece.Type.KNIGHT)
        );
    }

}
