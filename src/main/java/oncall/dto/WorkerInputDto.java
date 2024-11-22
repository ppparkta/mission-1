package oncall.dto;

import java.util.Arrays;
import java.util.List;
import oncall.constant.ExceptionMessage;

public record WorkerInputDto(List<String> members) {
    public static WorkerInputDto from(String[] workers) {
        if (workers.length < 5 || workers.length > 35) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }

        for (String worker : workers) {
            if (worker.length() > 5) {
                throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
            }
        }

        List<String> distinctWorkers = Arrays.stream(workers).distinct().toList();
        if (workers.length != distinctWorkers.size()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }

        return new WorkerInputDto(Arrays.stream(workers).toList());
    }

    public int getSize() {
        return members.size();
    }

    public static boolean isIncludeDayOff(String weekWorker, WorkerInputDto dayOffWorkerInputDto) {
        for (String dayOffWorker : dayOffWorkerInputDto.members()) {
            if (dayOffWorker.equals(weekWorker)) {
                return true;
            }
        }
        return false;
    }
}
