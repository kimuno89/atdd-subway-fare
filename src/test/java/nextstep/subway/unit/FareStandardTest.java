package nextstep.subway.unit;

import nextstep.subway.domain.FareStandard;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FareStandardTest {

    @ParameterizedTest
    @CsvSource({"10, 0", "11, 5", "51, 8"})
    void decide(int totalDistance, int distance) {
        // when
        FareStandard standard = FareStandard.decide(totalDistance);

        // then
        assertThat(standard.getDistance()).isEqualTo(distance);
    }
}