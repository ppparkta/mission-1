package oncall.constant;

public enum HoliDay {
    신정(1, 1),
    삼일절(3, 1),
    어린이날(5, 5),
    현충일(6, 6),
    광복절(8, 15),
    개천절(10, 3),
    한글날(10, 9),
    성탄절(12, 25),
    ;
    private final Integer month;
    private final Integer day;

    HoliDay(Integer month, Integer day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(Integer month, Integer day) {
        for (HoliDay holiDay : HoliDay.values()) {
            if (month == holiDay.month && day == holiDay.day) {
                return true;
            }
        }
        return false;
    }
}
