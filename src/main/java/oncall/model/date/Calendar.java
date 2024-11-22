package oncall.model.date;

import oncall.constant.ExceptionMessage;

public enum Calendar {
    JAN(1, 31),
    FEB(2, 28),
    MAR(3, 31),
    APR(4, 30),
    MAY(5, 31),
    JUN(6, 30),
    JUL(7, 31),
    AUG(8, 31),
    SEP(9, 30),
    OCT(10, 31),
    NOV(11, 30),
    DEC(12, 31),
    ;
    private final Integer month;
    private final Integer lastDay;

    Calendar(Integer month, Integer lastDay) {
        this.month = month;
        this.lastDay = lastDay;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getLastDay() {
        return lastDay;
    }

    public static Calendar from(int month) {
        for (Calendar calendar : Calendar.values()) {
            if (calendar.month == month) {
                return calendar;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
    }
}
