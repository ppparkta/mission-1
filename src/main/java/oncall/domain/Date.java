package oncall.domain;


public class Date {
    private int month;
    private Day day;

    public Date(int month, Day day) {
        this.month = month;
        this.day = day;
    }
    public static Date asDate (int month, String day) {
        if (asDay(day) == Day.NONE){
            throw new IllegalArgumentException();
        }
        return new Date(month, asDay(day));
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

    public int getLastDay() {
        if (this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7 || this.month == 8) {
            return 31;
        }
        if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) {
            return 30;
        }
        if (this.month == 2) {
            return 28;
        }
        return 0;
    }

    public int getMonth() {
        return this.month;
    }
    public Day getDay() {
        return this.day;
    }


    public boolean isHoliday(int dayNum) {
        if (this.day == Day.SATURDAY || this.day == Day.SUNDAY) {
            return true;
        }
        if ((this.month == 1 && dayNum == 1) || (this.month == 3 && dayNum == 1) || (this.month == 5 && dayNum == 5)
            || (this.month == 6 && dayNum == 6) || (this.month == 8 && dayNum == 15) || (this.month == 10 && dayNum == 3)
            || (this.month == 10 && dayNum == 9) || (this.month == 12 && dayNum == 15))
        { return true; }
        return false;
    }






}
