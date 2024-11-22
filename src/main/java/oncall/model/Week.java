package oncall.model;

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

    public static Week of(String week) {
        for (Week weekValue : values()) {
            if (week.equals(weekValue.week)) {
                return weekValue;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
    }

    public static Week from(Integer localDateTimeWeek) {
        for (Week weekValue : values()) {
            if (localDateTimeWeek == weekValue.localDateTimeWeek) {
                return weekValue;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
    }

    public static Week nextWeek(Week week) {
        for (Week newtWeek : Week.values()) {
            if (week.localDateTimeWeek == 7) {
                return Week.from(1);
            }
        }
        return Week.from(week.localDateTimeWeek + 1);
    }

    public boolean isDayOff() {
        return this.localDateTimeWeek == 6 || this.localDateTimeWeek == 7;
    }
}
