package oncall.model.date;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoliDayTest {
    @DisplayName("공휴일인지 판단한다")
    @Test
    void 공휴일_확인() {
        // given
        Integer month = 5;
        Integer day = 5;

        // when
        boolean holiday = HoliDay.isHoliday(month, day);

        // then
        Assertions.assertThat(holiday).isTrue();
    }

}