package nextstep.subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class LinePolicyTest {

    @ParameterizedTest(name = "{0}들을 경유 했을때 노선에 따른 부가 요금은 {1}원이다.")
    @MethodSource("generateData")
    void calculateFare(List<Line> lines, int fare) {

        //when
        int result = new LineFarePolicy(lines).calculate();

        //then
        assertThat(result).isEqualTo(fare);
    }
    static Stream<Arguments> generateData() {
        Line 일호선 = new Line("일호선", "", 500);
        Line 이호선 = new Line("이호선", "", 100);
        Line 삼호선 = new Line("삼호선", "", 0);
        Line 사호선 = new Line("사호선", "", 300);
        Line 분당선 = new Line("분당선", "", 400);
        Line 신분당선 = new Line("신분당선", "", 1_000);

        return Stream.of(
                Arguments.of(Arrays.asList(일호선, 이호선, 삼호선), 500),
                Arguments.of(Arrays.asList(일호선, 이호선, 사호선), 500),
                Arguments.of(Arrays.asList(일호선, 분당선, 신분당선), 1_000),
                Arguments.of(Arrays.asList(이호선, 삼호선, 신분당선), 1_000),
                Arguments.of(Arrays.asList(삼호선, 사호선, 분당선), 400),
                Arguments.of(Arrays.asList(사호선, 신분당선, 분당선), 1_000)
        );
    }
}