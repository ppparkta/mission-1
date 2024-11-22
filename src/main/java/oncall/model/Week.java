package oncall.model;

import java.util.Optional;
import oncall.constant.ExceptionMessage;

public enum Week {
    MON("월", 1),
    TUE("화", 2),
    WED("수", 3),
    THU("목", 4),
    FRI("금", 5),
    SAT("토", 6),
    SUN("일", 7),
    ;
    private final String week;
    private final int localDateTimeWeek;

    Week(String week, int localDateTimeWeek) {
        this.week = week;
        this.localDateTimeWeek = localDateTimeWeek;
    }

    public String getWeek() {
        return week;
    }

    public int getLocalDateTimeWeek() {
        return localDateTimeWeek;
    }

    public static Week of(String week) {
        for (Week weekValue : values()) {
            if (week.equals(weekValue.week)) {
                return weekValue;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
    }
}