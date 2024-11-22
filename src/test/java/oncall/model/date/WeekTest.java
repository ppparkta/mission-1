package oncall.model.date;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekTest {
    @DisplayName("다음 요일이 선택되는지 확인한다")
    @Test
    void 요일_넘기기_테스트(){
        // given
        Week mon = Week.of("월");
        Week sun = Week.of("일");

        // when
        Week monNext = Week.nextWeek(mon);
        Week sunNext = Week.nextWeek(sun);

        // then
        org.assertj.core.api.Assertions.assertThat(monNext).isEqualTo(Week.TUE);
        org.assertj.core.api.Assertions.assertThat(sunNext).isEqualTo(Week.MON);
    }

}