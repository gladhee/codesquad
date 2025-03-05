package org.chess.domain.board;

import org.chess.domain.pieces.Pawn;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    private Pawn whitePawn;
    private Pawn blackPawn;

    @BeforeEach
    void setUp() {
        this.whitePawn = new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        this.blackPawn = new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    @Test
    @DisplayName("보드 생성 후 Pawn을 추가하고 저장할 수 있어야 한다")
    void create() throws Exception {
        Board board = new Board();

        verifyAddedPawnInBoard(board, whitePawn, 1);
        verifyAddedPawnInBoard(board, blackPawn, 2);
    }

    void verifyAddedPawnInBoard(Board board, Pawn pawn, int count) {
        board.add(pawn);
        assertEquals(count, board.size());
        assertEquals(pawn, board.findPawn(count - 1));
    }

    @Test
    @DisplayName("보드 생성 후 폰을 초기화시 흰색 폰과 검은색 폰이 각각 8개씩 생성되어야 한다")
    void initialize() throws Exception {
        Board board = new Board();
        board.initialize();
        assertThat("pppppppp").isEqualTo(board.getWhitePawnsResult());
        assertThat("PPPPPPPP").isEqualTo(board.getBlackPawnsResult());
    }

}
