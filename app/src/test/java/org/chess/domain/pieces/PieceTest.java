package org.chess.domain.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPawn(Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);
    }

    void verifyPawn(final String color, final char representation) {
        Piece piece = new Piece(color, representation);
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

    @Test
    @DisplayName("기본 생성자로 흰색 폰이 생성되어야 한다")
    void create_기본생성자() throws Exception {
        Piece piece = new Piece();
        assertThat(Piece.WHITE_COLOR).isEqualTo(piece.getColor());
        assertThat(Piece.WHITE_PAWN_REPRESENTATION).isEqualTo(piece.getRepresentation());
    }

}
