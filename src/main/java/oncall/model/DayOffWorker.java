package oncall.model;

import java.util.List;

public class DayOffWorker extends Worker {
    public DayOffWorker(List<String> dayOffWorkers) {
        for (String worker : dayOffWorkers) {
            this.workers.add(worker);
        }
        currentIndex = 0;
    }
}
