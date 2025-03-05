package org.chess.domain.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    void verifyPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(pawn.getRepresentation()).isEqualTo(representation);
    }

    @Test
    @DisplayName("기본 생성자로 흰색 폰이 생성되어야 한다")
    void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertThat(Pawn.WHITE_COLOR).isEqualTo(pawn.getColor());
        assertThat(Pawn.WHITE_REPRESENTATION).isEqualTo(pawn.getRepresentation());
    }

}
