package oncall.domain;

import java.util.Collections;
import java.util.List;


public class WorkPermutation {

    private List<Worker> workers;
    private WorkType workType;

    public WorkPermutation(List<Worker> workers, WorkType workType) {
        this.workers = workers;
        this.workType = workType;
    }

    public static WorkPermutation of(List<Worker> workers, WorkType workType) {
        WorkPermutation permutation = new WorkPermutation(workers, workType);
        return permutation;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void workkerChange(int holidayTurnPoint) {
        int swapPoint = 0;
        if (holidayTurnPoint + 1 == workers.size()) {
            swapPoint = 0;
        }
        if (holidayTurnPoint + 1 < workers.size()) {
            swapPoint = holidayTurnPoint+1;
        }
        Collections.swap(workers, holidayTurnPoint, swapPoint);

    }


}

