package org.chess.domain.board;

import org.chess.domain.pieces.Piece;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.chess.utils.StringUtils.appendNewLine;

class BoardTest {

    private Piece whitePawn;
    private Piece blackPawn;

    @BeforeEach
    void setUp() {
        this.whitePawn = Piece.createWhitePawn();
        this.blackPawn = Piece.createBlackPawn();
    }

    @Test
    @DisplayName("보드 생성 후 Pawn을 추가하고 저장할 수 있어야 한다")
    void create() throws Exception {
        // given
        Board board = new Board();

        // when
        board.add(whitePawn);

        // then
        assertThat(1).isEqualTo(board.pieceCount());
        assertThat(whitePawn).isEqualTo(board.findPawn(0));

        // when
        board.add(blackPawn);

        // then
        assertThat(2).isEqualTo(board.pieceCount());
        assertThat(blackPawn).isEqualTo(board.findPawn(1));
    }

    @Test
    @DisplayName("보드 생성 후 폰을 초기화시 흰색 폰과 검은색 폰이 각각 8개씩 생성되어야 한다")
    void initialize() throws Exception {
        // given
        Board board = new Board();

        // when
        board.initialize();

        // then
        assertThat("pppppppp").isEqualTo(board.getPawnsResultWith(6));
        assertThat("PPPPPPPP").isEqualTo(board.getPawnsResultWith(1));
    }

    @Test
    @DisplayName("보드 initailize 후 모든 기물이 생성되어야 한다")
    void initializeAllPieces() throws Exception {
        // given
        Board board = new Board();

        // when
        board.initialize();

        // then
        assertThat(board.pieceCount()).isEqualTo(32);

        String blankRank = appendNewLine("........");

        assertThat(appendNewLine("RNBQKBNR") +
                appendNewLine("PPPPPPPP") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("pppppppp") +
                appendNewLine("rnbqkbnr"))
                .isEqualTo(board.showBoard());
    }

}
