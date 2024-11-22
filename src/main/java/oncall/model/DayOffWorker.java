package oncall.model;

import java.util.ArrayList;
import java.util.List;

public class DayOffWorker {
    List<String> workers = new ArrayList<>();

    public DayOffWorker(List<String> workers) {
        this.workers = workers;
    }
}
