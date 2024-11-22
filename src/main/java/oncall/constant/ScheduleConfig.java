package oncall.constant;

public enum ScheduleConfig {
    SCHEDULE_SPLIT_MONTH(0),

    SCHEDULE_SPLIT_WEEK(1),
    ;

    private final Integer value;

    ScheduleConfig(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
