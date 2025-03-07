package org.chess.domain.board;

import org.chess.domain.pieces.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("Rank 내에 Pieces를 반환할 수 있어야 한다")
    void Rank_내에_Pieces_반환_테스트() {
        // given
        List<Piece> pieces = List.of(
                Piece.createWhite(Piece.Type.ROOK), Piece.createWhite(Piece.Type.KNIGHT), Piece.createWhite(Piece.Type.BISHOP),
                Piece.createWhite(Piece.Type.QUEEN), Piece.createWhite(Piece.Type.KING), Piece.createWhite(Piece.Type.BISHOP),
                Piece.createWhite(Piece.Type.KNIGHT), Piece.createWhite(Piece.Type.ROOK)
        );

        // when
        Rank rank = Rank.of(pieces);

        // then
        assertThat(pieces).isEqualTo(rank.getPieces());
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
