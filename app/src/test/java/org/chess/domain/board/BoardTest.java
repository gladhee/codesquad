package org.chess.domain.board;

import org.chess.domain.pieces.Piece;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.chess.utils.StringUtils.appendNewLine;

class BoardTest {

    private Piece whitePawn;
    private Piece blackPawn;

    @BeforeEach
    void setUp() {
        this.whitePawn = Piece.createWhite(Piece.Type.PAWN);
        this.blackPawn = Piece.createBlack(Piece.Type.PAWN);
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

    @Test
    @DisplayName("Piece 의 색과 종류를 반환할 수 있어야 한다")
    void 기물의_개수_반환_테스트() {
        // given
        Board board = new Board();

        // when
        board.initialize();

        // then
        assertThat(8).isEqualTo(board.countPieces(Piece.Color.WHITE, Piece.Type.PAWN));
        assertThat(2).isEqualTo(board.countPieces(Piece.Color.WHITE, Piece.Type.ROOK));
        assertThat(2).isEqualTo(board.countPieces(Piece.Color.WHITE, Piece.Type.KNIGHT));
    }

    @Test
    @DisplayName("임의의 주어진 위치의 기물을 조회할 수 있어야 한다")
    void 기물_조회_테스트() {
        // given
        Board board = new Board();

        // when
        board.initialize();

        // then
        assertThat(Piece.createBlack(Piece.Type.ROOK))
                .isEqualTo(board.findPiece("h8"));
        assertThat(Piece.createWhite(Piece.Type.ROOK))
                .isEqualTo(board.findPiece("h1"));
        assertThat(Piece.createBlack(Piece.Type.ROOK))
                .isEqualTo(board.findPiece("a8"));
        assertThat(Piece.createWhite(Piece.Type.ROOK))
                .isEqualTo(board.findPiece("a1"));
    }

    @Test
    @DisplayName("임의의 기물을 체스판위에 추가할 수 있어야 한다")
    void 기물_추가_테스트() {
        // given
        Board board = new Board();
        board.initializeEmptyBoard();

        String position = "b5";
        Piece piece = Piece.createWhite(Piece.Type.ROOK);
        board.move(position, piece);

        assertThat(piece).isEqualTo(board.findPiece(position));
    }

    @Test
    @DisplayName("현재까지 남아있는 기물에 따라 점수를 계산할 수 있어야 한다")
    void 점수_계산_테스트() {
        // given
        Board board = new Board();
        board.initializeEmptyBoard();

        // when
        addPiece(board, "b6", Piece.createBlack(Piece.Type.PAWN));
        addPiece(board, "e6", Piece.createBlack(Piece.Type.QUEEN));
        addPiece(board, "b8", Piece.createBlack(Piece.Type.KING));
        addPiece(board, "c8", Piece.createBlack(Piece.Type.ROOK));

        addPiece(board, "f2", Piece.createWhite(Piece.Type.PAWN));
        addPiece(board, "g2", Piece.createWhite(Piece.Type.PAWN));
        addPiece(board, "e1", Piece.createWhite(Piece.Type.ROOK));
        addPiece(board, "f1", Piece.createWhite(Piece.Type.KING));

        // then
        assertThat(board.calculatePoint(Piece.Color.BLACK)).isCloseTo(15.0, within(0.01));
        assertThat(board.calculatePoint(Piece.Color.WHITE)).isCloseTo(7.0, within(0.01));

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("검은색과 흰색 기물을 구분해서 점수가 높은 순으로 정렬할 수 있어야 한다")
    void 기물_정렬_테스트() {
        // given
        Board board = new Board();
        board.initializeEmptyBoard();

        // when
        addPiece(board, "b6", Piece.createBlack(Piece.Type.PAWN));
        addPiece(board, "e6", Piece.createBlack(Piece.Type.QUEEN));
        addPiece(board, "b8", Piece.createBlack(Piece.Type.KING));
        addPiece(board, "c8", Piece.createBlack(Piece.Type.ROOK));

        addPiece(board, "f2", Piece.createWhite(Piece.Type.PAWN));
        addPiece(board, "g2", Piece.createWhite(Piece.Type.PAWN));
        addPiece(board, "e1", Piece.createWhite(Piece.Type.ROOK));
        addPiece(board, "f1", Piece.createWhite(Piece.Type.KING));

        // then
        List<Piece> actualBlackPiecesWithAsc = board.sortPiecesByAscending(Piece.Color.BLACK);
        List<Piece> actualWhitePiecesWithDesc = board.sortPiecesByDescending(Piece.Color.WHITE);
        List<Piece> actualBlackPiecesWithDesc = board.sortPiecesByDescending(Piece.Color.BLACK);
        List<Piece> actualWhitePiecesWithAsc = board.sortPiecesByAscending(Piece.Color.WHITE);

        List<Piece> expectedBlackPiecesWithAsc = List.of(
                Piece.createBlack(Piece.Type.KING),   // 0.0
                Piece.createBlack(Piece.Type.PAWN),   // 1.0
                Piece.createBlack(Piece.Type.ROOK),   // 5.0
                Piece.createBlack(Piece.Type.QUEEN)   // 9.0
        );

        List<Piece> expectedBlackPiecesWithDesc = List.of(
                Piece.createBlack(Piece.Type.QUEEN),  // 9.0
                Piece.createBlack(Piece.Type.ROOK),   // 5.0
                Piece.createBlack(Piece.Type.PAWN),   // 1.0
                Piece.createBlack(Piece.Type.KING)    // 0.0
        );

        List<Piece> expectedWhitePiecesWithAsc = List.of(
                Piece.createWhite(Piece.Type.KING),   // 0.0
                Piece.createWhite(Piece.Type.PAWN),   // 1.0
                Piece.createWhite(Piece.Type.PAWN),   // 1.0
                Piece.createWhite(Piece.Type.ROOK)    // 5.0
        );

        List<Piece> expectedWhitePiecesWithDesc = List.of(
                Piece.createWhite(Piece.Type.ROOK),   // 5.0
                Piece.createWhite(Piece.Type.PAWN),   // 1.0
                Piece.createWhite(Piece.Type.PAWN),   // 1.0
                Piece.createWhite(Piece.Type.KING)    // 0.0
        );

        compare(actualBlackPiecesWithAsc, expectedBlackPiecesWithAsc);
        compare(actualWhitePiecesWithDesc, expectedWhitePiecesWithDesc);
        compare(actualBlackPiecesWithDesc, expectedBlackPiecesWithDesc);
        compare(actualWhitePiecesWithAsc, expectedWhitePiecesWithAsc);
    }

    private void addPiece(Board board, String Position, Piece piece) {
        board.move(Position, piece);
    }

    private void compare(List<Piece> actual, List<Piece> expected) {
        assertThat(actual.size()).isEqualTo(expected.size());
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i)).isEqualTo(expected.get(i));
        }
    }

}
