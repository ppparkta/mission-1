package oncall.domain;

public class Date {
    private int month;
    private Day day;

    public Date(int month, Day Day) {
        this.month = month;
        this.day = day;
    }
    public static Date asDate (int month, String day) {
        if (asDay(day) == Day.NONE){
            throw new IllegalArgumentException();
        }
        return new Date(month, asDay(day));
    }

    enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY, NONE;
    }

    public static Day asDay(String day) {
        if (day.equals("일")) {return Day.SUNDAY;}
        if (day.equals("월")) {return Day.MONDAY;}
        if (day.equals("화")) {return Day.TUESDAY;}
        if (day.equals("수")) {return Day.WEDNESDAY;}
        if (day.equals("목")) {return Day.THURSDAY;}
        if (day.equals("금")) {return Day.FRIDAY;}
        if (day.equals("토")) {return Day.SATURDAY;}
        return Day.NONE;
    }


}
