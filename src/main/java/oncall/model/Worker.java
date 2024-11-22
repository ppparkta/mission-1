package oncall.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Worker {
    protected final List<String> workers = new ArrayList<>();
    protected Integer currentIndex;
    protected boolean skip;

    public List<String> getWorkers() {
        return List.copyOf(workers);
    }

    protected void setSkip() {
        this.skip = true;
    }

    protected void setCurrentIndex(int index) {
        this.currentIndex = index;
    }

    protected void plusCurrentIndex() {
        int sumDay = 1;
        if (skip) {
            sumDay = 2;
        }
        if (currentIndex != 0 && (currentIndex + sumDay) % workers.size() == 0) {
            currentIndex = 0;
        } else {
            if (currentIndex + sumDay < workers.size() - 1) {
                currentIndex = currentIndex + sumDay;
            } else {
                currentIndex = (currentIndex + sumDay) % workers.size();
            }
        }
        if (skip) {
            skip = false;
        }
    }

    public void minusCurrentIndex() {
        if ((currentIndex - 1) < 0) {
            currentIndex = workers.size() - 1;
        } else {
            currentIndex = currentIndex - 1;
        }
    }
}
