package oncall.constant;

public enum WorkerConfig {
    WEEK(0),
    DAY_OFF(1),
    ;
    private final Integer value;

    WorkerConfig(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
