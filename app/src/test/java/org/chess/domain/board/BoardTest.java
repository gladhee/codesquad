package org.chess.domain.board;

import org.chess.domain.pieces.Pawn;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private Pawn whitePawn;
    private Pawn blackPawn;

    @BeforeEach
    void setUp() {
        this.whitePawn = new Pawn(Pawn.WHITE_COLOR);
        this.blackPawn = new Pawn(Pawn.BLACK_COLOR);
    }

    @Test
    public void create() throws Exception {
        Board board = new Board();

        int count = 0;
        verifyAddedPawnInBoard(board, whitePawn, ++count);
        verifyAddedPawnInBoard(board, blackPawn, ++count);
    }

    private void verifyAddedPawnInBoard(Board board, Pawn pawn, int count) {
        board.add(pawn);
        assertEquals(count, board.size());
        assertEquals(pawn, board.findPawn(count - 1));
    }

}
