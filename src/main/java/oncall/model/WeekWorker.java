package oncall.model;

import java.util.List;

public class WeekWorker extends Worker {
    public WeekWorker(List<String> weekWorkers) {
        for (String worker : weekWorkers) {
            this.workers.add(worker);
        }
        currentIndex = 0;
    }
}
