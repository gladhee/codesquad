package org.chess.domain.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    private Board board;

    @BeforeEach
    void setUp() {
        this.board = Board.create();
    }

    @Test
    @DisplayName("'a8'같은 문자열 좌표를 분리할 수 있어야 한다")
    void Postion_좌표_분리_테스트() {
        // given
        String pos = "a8";

        // when
        Position position = Position.of(pos);

        // then
        Assertions.assertThat(position.y()).isEqualTo(0);
        Assertions.assertThat(position.x()).isEqualTo(0);
    }

    @Test
    @DisplayName("좌표가 같은 Position 객체는 equals() 메소드 결과가 true여야 한다")
    void 동등성_비교_테스트() {
        // given
        Position pos1 = Position.of(0, 0);
        Position pos2 = Position.of(0, 0);

        // when
        boolean result = pos1.equals(pos2);

        // then
        Assertions.assertThat(result).isTrue();
    }

}
