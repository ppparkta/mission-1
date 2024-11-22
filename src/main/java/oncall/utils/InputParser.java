package oncall.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.constant.ExceptionMessage;
import oncall.constant.ScheduleConfig;
import oncall.dto.ScheduleInputDto;
import oncall.dto.WorkerInputDto;

public class InputParser {
    public static ScheduleInputDto parseSchedule(String value) {
        String[] splitSchedule = value.split(",");
        if (splitSchedule.length != 2) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
        for (String schedule : splitSchedule) {
            if (schedule.isBlank()) {
                throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
            }
        }
        return ScheduleInputDto.of(splitSchedule[ScheduleConfig.SCHEDULE_SPLIT_MONTH.getValue()],
                splitSchedule[ScheduleConfig.SCHEDULE_SPLIT_WEEK.getValue()]);
    }

    public static WorkerInputDto parseWorker(String value) {
        String[] splitWorkers = value.split(",");

        Arrays.stream(splitWorkers).forEach(worker -> {
            if (worker.isBlank()) {
                throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
            }
        });
        return WorkerInputDto.from(splitWorkers);
    }
}
