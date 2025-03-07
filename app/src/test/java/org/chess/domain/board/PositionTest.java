package org.chess.domain.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new Board();
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

}
