package org.chess.domain.board;

import org.chess.domain.pieces.Pawn;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    private Pawn whitePawn;
    private Pawn blackPawn;

    @BeforeEach
    void setUp() {
        this.whitePawn = new Pawn(Pawn.WHITE_COLOR);
        this.blackPawn = new Pawn(Pawn.BLACK_COLOR);
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

}
