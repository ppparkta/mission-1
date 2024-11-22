package oncall.dto;

import oncall.constant.ExceptionMessage;
import oncall.model.date.Week;

public record ScheduleInputDto(int month, String week) {
    public static ScheduleInputDto of(String month, String week) {
        try {
            int parseMonth = Integer.parseInt(month);
            if (parseMonth < 1 || parseMonth > 12) {
                throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
            }
            Week weekEnum = Week.of(week);
            return new ScheduleInputDto(parseMonth, weekEnum.getWeek());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }
}
