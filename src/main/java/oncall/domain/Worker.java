package oncall.domain;

import java.util.List;

public class Worker {
    private String name;

    public Worker(String name) {
        this.name = name;
    }
    public static Worker asWorker(String name) {
        return new Worker(name);
    }

    // 근무자 동명 확인 메서드
    public static boolean checkDuplicateName(List<Worker> workers, String name) {
        for (Worker worker : workers) {
            if (worker.name.equals(name)) {
                return false;
            }
        }
        return true;
    }
}
