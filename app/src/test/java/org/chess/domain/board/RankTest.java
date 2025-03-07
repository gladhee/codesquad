package org.chess.domain.board;

import org.chess.domain.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class RankTest {

    private List<Piece> readyPieces;

    @BeforeEach
    void setUp() {
        readyPieces = List.of(
                Piece.createWhite(Piece.Type.ROOK), Piece.createBlack(Piece.Type.KNIGHT), Piece.createWhite(Piece.Type.BISHOP),
                Piece.createWhite(Piece.Type.QUEEN), Piece.createBlack(Piece.Type.KING), Piece.createWhite(Piece.Type.BISHOP),
                Piece.createWhite(Piece.Type.KNIGHT), Piece.createBlack(Piece.Type.ROOK)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    @DisplayName("특정 위치의 Piece 를 가져올 수 있어야 한다")
    void Piece_특정위치_가져오기_테스트(int index) {
        // given
        List<Piece> pieces = new ArrayList<>(readyPieces);
        Rank rank = Rank.of(pieces);

        // when
        Piece piece = rank.getPieceAt(index);

        // then
        assertThat(piece).isEqualTo(readyPieces.get(index));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8, 40, 100, -200})
    @DisplayName("Rank 의 Piece 를 가져올 때 Index 가 범위를 벗어나면 예외가 발생해야 한다")
    void Piece_범위를_벗어난_인덱스_테스트(int index) {
        // given
        List<Piece> pieces = new ArrayList<>(readyPieces);
        Rank rank = Rank.of(pieces);

        // when & then
        assertThatThrownBy(() -> rank.getPieceAt(index))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Index out of range");
    }

    @Test
    @DisplayName("특정 위치에 Piece 를 추가할 수 있어야 한다")
    void Piece_특정위치에_추가_테스트() {
        // given
        List<Piece> pieces = new ArrayList<>(readyPieces);

        Rank rank = Rank.of(pieces);
        Piece piece = Piece.createBlack(Piece.Type.PAWN);

        // when
        rank.placePieceAt(0, piece);

        // then
        assertThat(rank.getPieceAt(0)).isEqualTo(piece);
    }

    @Test
    @DisplayName("현재 Rank 에 대해 특정 색상들의 점수를 계산할 수 있어야 한다")
    void 색상별_점수_계산_테스트() {
        // given
        List<Piece> pieces = new ArrayList<>(readyPieces);
        Rank rank = Rank.of(pieces);

        // when
        double whitePoints = rank.calculatePoints(Piece.Color.WHITE);
        double blackPoints = rank.calculatePoints(Piece.Color.BLACK);

        // then
        assertThat(whitePoints).isCloseTo(22.5, within(0.01));
        assertThat(blackPoints).isEqualTo(7.5, within(0.01));
    }

    @Test
    @DisplayName("현재 Rank에 특정 색상과 타입의 Piece 가 몇 개 있는지 계산할 수 있어야 한다")
    void 색상과_타입별_개수_계산_테스트() {
        // given
        List<Piece> pieces = new ArrayList<>(readyPieces);
        Rank rank = Rank.of(pieces);

        // when
        int whiteRookCount = rank.countPieces(Piece.Color.WHITE, Piece.Type.ROOK);
        int blackKnightCount = rank.countPieces(Piece.Color.BLACK, Piece.Type.KNIGHT);

        // then
        assertThat(whiteRookCount).isEqualTo(1);
        assertThat(blackKnightCount).isEqualTo(1);
    }

    @Test
    @DisplayName("현재 Rank 에서 특정 색상의 Piece 들을 가져올 수 있어야 한다")
    void 색상별_Piece_가져오기_테스트() {
        // given
        List<Piece> pieces = new ArrayList<>(readyPieces);
        Rank rank = Rank.of(pieces);

        // when
        List<Piece> whitePieces = rank.getPiecesByColor(Piece.Color.WHITE);
        List<Piece> blackPieces = rank.getPiecesByColor(Piece.Color.BLACK);

        // then
        assertThat(whitePieces).containsExactly(
                Piece.createWhite(Piece.Type.ROOK), Piece.createWhite(Piece.Type.BISHOP),
                Piece.createWhite(Piece.Type.QUEEN), Piece.createWhite(Piece.Type.BISHOP),
                Piece.createWhite(Piece.Type.KNIGHT)
        );
        assertThat(blackPieces).containsExactly(
                Piece.createBlack(Piece.Type.KNIGHT), Piece.createBlack(Piece.Type.KING),
                Piece.createBlack(Piece.Type.ROOK)
        );
    }

    @Test
    @DisplayName("현재 Rank 에 Pieces 가 색상에 따라 출력되어야 한다")
    void 색상에_따른_반환_테스트() {
        // given
        List<Piece> pieces = List.of(
                Piece.createWhite(Piece.Type.ROOK), Piece.createBlack(Piece.Type.KNIGHT), Piece.createWhite(Piece.Type.BISHOP),
                Piece.createWhite(Piece.Type.QUEEN), Piece.createBlack(Piece.Type.KING), Piece.createWhite(Piece.Type.BISHOP),
                Piece.createWhite(Piece.Type.KNIGHT), Piece.createBlack(Piece.Type.ROOK)
        );
        Rank rank = Rank.of(pieces);

        // when
        String rankString = rank.toString();

        // then
        assertThat(rankString).isEqualTo("rNbqKbnR");
    }

}
