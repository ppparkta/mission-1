package oncall.dto;

import oncall.model.Week;

public record ScheduleOutputDto(Integer month, Integer day, Week week, boolean isHoliday, String workerName) {
}
