package oncall.model;

import java.util.ArrayList;
import java.util.List;

public class WeekWorker {
    List<String> workers = new ArrayList<>();

    public WeekWorker(List<String> workers) {
        this.workers = workers;
    }
}
