package oncall.dto;

import oncall.model.date.Week;

public record ScheduleOutputDto(Integer month, Integer day, Week week, boolean isHoliday, String workerName) {
}
