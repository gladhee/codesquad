package org.chess.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    @DisplayName("문자열을 주면 개행 문자를 추가한 문자열을 반환한다.")
    void 문자열_개행문자_추가_테스트() {
        String hello = "hello";
        String expected = "hello" + StringUtils.NEWLINE;

        String actual = StringUtils.appendNewLine(hello);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

}
