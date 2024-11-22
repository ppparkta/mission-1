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

    protected void plusCurrentIndex() {
        int sumDay = 1;
        if (skip) {
            sumDay = 2;
        }
        if (isFullCircle(sumDay)) {
            return;
        }
        if (checkOutBound(sumDay)) {
            return;
        }
        currentIndex = (currentIndex + sumDay) % workers.size();
        if (skip) {
            skip = false;
        }
    }

    private boolean checkOutBound(int sumDay) {
        if (currentIndex + sumDay < workers.size() - 1) {
            currentIndex = currentIndex + sumDay;
            if (skip) {
                skip = false;
            }
            return true;
        }
        return false;
    }

    private boolean isFullCircle(int sumDay) {
        if (currentIndex != 0 && (currentIndex + sumDay) % workers.size() == 0) {
            currentIndex = 0;
            if (skip) {
                skip = false;
            }
            return true;
        }
        return false;
    }

    public void minusCurrentIndex() {
        if ((currentIndex - 1) < 0) {
            currentIndex = workers.size() - 1;
            return;
        }
        currentIndex = currentIndex - 1;
    }
}
