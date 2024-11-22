package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public enum Day {
    SUNDAY , MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY, NONE;

    public static List<Day> getDays() {
        List<Day> days = new ArrayList<>();
        days.add(SUNDAY);
        days.add(MONDAY);
        days.add(TUESDAY);
        days.add(WEDNESDAY);
        days.add(THURSDAY);
        days.add(FRIDAY);
        days.add(SATURDAY);
        return days;
    }
    public static String getDayString(int day) {
        if (day == SUNDAY.ordinal()) { return "일";}
        if (day == MONDAY.ordinal()) { return "월";}
        if (day == TUESDAY.ordinal()) { return "화";}
        if (day == WEDNESDAY.ordinal()) { return "수";}
        if (day == THURSDAY.ordinal()) { return "목";}
        if (day == FRIDAY.ordinal()) { return "금";}
        if (day == SATURDAY.ordinal()) { return "토";}
        return "NONE";
    }



}