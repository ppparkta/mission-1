package oncall.model;

import java.time.Month;
import oncall.constant.ExceptionMessage;

public class Schedule {
    private final Month startMonth;
    private final Week startWeek;

    public Schedule(int startMonth, String startWeek) {
        validate(startMonth);
        this.startMonth = Month.of(startMonth);
        this.startWeek = Week.of(startWeek);
    }

    private void validate(int startMonth) {
        if (startMonth < 1 || startMonth > 12) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }
}
