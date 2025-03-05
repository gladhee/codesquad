package org.chess.domain.board;

import org.chess.domain.pieces.Piece;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    private Piece whitePiece;
    private Piece blackPiece;

    @BeforeEach
    void setUp() {
        this.whitePiece = new Piece(Piece.WHITE_COLOR, Piece.WHITE_REPRESENTATION);
        this.blackPiece = new Piece(Piece.BLACK_COLOR, Piece.BLACK_REPRESENTATION);
    }

    @Test
    @DisplayName("보드 생성 후 Pawn을 추가하고 저장할 수 있어야 한다")
    void create() throws Exception {
        Board board = new Board();

        verifyAddedPawnInBoard(board, whitePiece, 1);
        verifyAddedPawnInBoard(board, blackPiece, 2);
    }

    void verifyAddedPawnInBoard(Board board, Piece piece, int count) {
        board.add(piece);
        assertEquals(count, board.size());
        assertEquals(piece, board.findPawn(count - 1));
    }

    @Test
    @DisplayName("보드 생성 후 폰을 초기화시 흰색 폰과 검은색 폰이 각각 8개씩 생성되어야 한다")
    void initialize() throws Exception {
        Board board = new Board();
        board.initialize();
        assertThat("pppppppp").isEqualTo(board.getPawnsResultWith(6));
        assertThat("PPPPPPPP").isEqualTo(board.getPawnsResultWith(1));
    }

}
