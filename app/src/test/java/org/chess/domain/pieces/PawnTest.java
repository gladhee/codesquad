package org.chess.domain.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.chess.domain.pieces.Pawn.BLACK_COLOR;
import static org.chess.domain.pieces.Pawn.WHITE_COLOR;

class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(WHITE_COLOR);
        verifyPawn(BLACK_COLOR);
    }

    void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertThat(WHITE_COLOR).isEqualTo(pawn.getColor());
    }

}
