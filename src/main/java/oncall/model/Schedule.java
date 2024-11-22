package oncall.model;

import java.util.ArrayList;
import java.util.List;
import oncall.constant.Calendar;
import oncall.constant.ExceptionMessage;
import oncall.constant.HoliDay;
import oncall.dto.ScheduleOutputDto;

public class Schedule {
    private final Calendar startMonth;
    private final Week startWeek;

    public Schedule(int startMonth, String startWeek) {
        validate(startMonth);
        this.startMonth = Calendar.from(startMonth);
        this.startWeek = Week.of(startWeek);
    }

    public List<ScheduleOutputDto> generate(WeekWorker weekWorker, DayOffWorker dayOffWorker) {
        String yesterdayWorker = "";
        Week currentWeek = this.startWeek;
        int currentDay = 1;
        List<ScheduleOutputDto> scheduleOutputDtos = new ArrayList<>();

        while (currentDay <= startMonth.getLastDay()) {
            yesterdayWorker = extractDayOffOrWeek(weekWorker, dayOffWorker, yesterdayWorker, currentWeek, currentDay,
                    scheduleOutputDtos);
            currentWeek = Week.nextWeek(currentWeek);
            currentDay++;
        }
        return scheduleOutputDtos;
    }

    private String extractDayOffOrWeek(WeekWorker weekWorker, DayOffWorker dayOffWorker, String yesterdayWorker,
                                       Week currentWeek,
                                       int currentDay, List<ScheduleOutputDto> scheduleOutputDtos) {
        if (currentWeek.isDayOff()) {
            yesterdayWorker = generateDayOffSchedule(dayOffWorker, yesterdayWorker, currentWeek, currentDay,
                    scheduleOutputDtos);
        }
        if (!currentWeek.isDayOff()) {
            yesterdayWorker = generateWeekSchecule(weekWorker, dayOffWorker, yesterdayWorker, currentWeek,
                    currentDay,
                    scheduleOutputDtos);
        }
        return yesterdayWorker;
    }

    private void validate(int startMonth) {
        if (startMonth < 1 || startMonth > 12) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }

    private String generateDayOffSchedule(DayOffWorker dayOffWorker, String yesterdayWorker, Week currentWeek,
                                          int currentDay,
                                          List<ScheduleOutputDto> scheduleOutputDtos) {
        String todayWorker;
        todayWorker = dayOffWorker.getWorkers().get(dayOffWorker.currentIndex);

        if (todayWorker.equals(yesterdayWorker)) {
            todayWorker = createDayOffSkipSchedule(dayOffWorker, currentWeek, currentDay, scheduleOutputDtos);
            yesterdayWorker = todayWorker;
            return yesterdayWorker;
        }
        createDayOffSchedule(dayOffWorker, todayWorker, currentWeek, currentDay, scheduleOutputDtos);
        yesterdayWorker = todayWorker;

        return yesterdayWorker;
    }

    private String generateWeekSchecule(WeekWorker weekWorker, DayOffWorker dayOffWorker, String yesterdayWorker,
                                        Week currentWeek,
                                        int currentDay, List<ScheduleOutputDto> scheduleOutputDtos) {
        if (HoliDay.isHoliday(startMonth.getMonth(), currentDay)) {
            yesterdayWorker = generateSkipWeek(dayOffWorker, yesterdayWorker, currentWeek, currentDay,
                    scheduleOutputDtos);
            return yesterdayWorker;
        }
        yesterdayWorker = generateNoSkipWeek(weekWorker, yesterdayWorker, currentWeek, currentDay,
                scheduleOutputDtos);
        return yesterdayWorker;
    }

    private String generateSkipWeek(DayOffWorker dayOffWorker, String yesterdayWorker, Week currentWeek, int currentDay,
                                    List<ScheduleOutputDto> scheduleOutputDtos) {
        String todayWorker = dayOffWorker.getWorkers().get(dayOffWorker.currentIndex);
        if (todayWorker.equals(yesterdayWorker)) {
            todayWorker = createHolidaySkipSchedule(dayOffWorker, currentWeek, currentDay,
                    scheduleOutputDtos);
            yesterdayWorker = todayWorker;
            return yesterdayWorker;
        }
        createHolidaySchedule(dayOffWorker, todayWorker, currentWeek, currentDay, scheduleOutputDtos);
        yesterdayWorker = todayWorker;
        return yesterdayWorker;
    }

    private String generateNoSkipWeek(WeekWorker weekWorker, String yesterdayWorker, Week currentWeek, int currentDay,
                                      List<ScheduleOutputDto> scheduleOutputDtos) {
        String todayWorker = weekWorker.getWorkers().get(weekWorker.currentIndex);
        if (todayWorker.equals(yesterdayWorker)) {
            todayWorker = createWeekStipSchedule(weekWorker, currentWeek, currentDay, scheduleOutputDtos);
            yesterdayWorker = todayWorker;
            return yesterdayWorker;
        }
        createWeekSchedule(weekWorker, todayWorker, currentWeek, currentDay, scheduleOutputDtos);
        yesterdayWorker = todayWorker;
        return yesterdayWorker;
    }

    private void createWeekSchedule(WeekWorker weekWorker, String todayWorker, Week currentWeek, int currentDay,
                                    List<ScheduleOutputDto> scheduleOutputDtos) {
        weekWorker.plusCurrentIndex();
        createOutputDto(scheduleOutputDtos, currentDay, currentWeek, false, todayWorker);
    }

    private String createWeekStipSchedule(WeekWorker weekWorker, Week currentWeek, int currentDay,
                                          List<ScheduleOutputDto> scheduleOutputDtos) {
        weekWorker.plusCurrentIndex();
        String todayWorker = weekWorker.getWorkers().get(weekWorker.currentIndex);
        weekWorker.setSkip();
        weekWorker.minusCurrentIndex();
        createOutputDto(scheduleOutputDtos, currentDay, currentWeek, false, todayWorker);
        return todayWorker;
    }

    private void createHolidaySchedule(DayOffWorker dayOffWorker, String todayWorker, Week currentWeek, int currentDay,
                                       List<ScheduleOutputDto> scheduleOutputDtos) {
        dayOffWorker.plusCurrentIndex();
        createOutputDto(scheduleOutputDtos, currentDay, currentWeek, true, todayWorker);
    }

    private String createHolidaySkipSchedule(DayOffWorker dayOffWorker, Week currentWeek, int currentDay,
                                             List<ScheduleOutputDto> scheduleOutputDtos) {
        dayOffWorker.plusCurrentIndex();
        String todayWorker = dayOffWorker.getWorkers().get(dayOffWorker.currentIndex);
        dayOffWorker.setSkip();
        dayOffWorker.minusCurrentIndex();
        createOutputDto(scheduleOutputDtos, currentDay, currentWeek, true, todayWorker);
        return todayWorker;
    }

    private void createDayOffSchedule(DayOffWorker dayOffWorker, String todayWorker, Week currentWeek, int currentDay,
                                      List<ScheduleOutputDto> scheduleOutputDtos) {
        dayOffWorker.plusCurrentIndex();
        createOutputDto(scheduleOutputDtos, currentDay, currentWeek, false, todayWorker);
    }

    private void createOutputDto(List<ScheduleOutputDto> scheduleOutputDtos, int currentDay, Week currentWeek,
                                 boolean isHoliday, String todayWorker) {
        scheduleOutputDtos.add(
                new ScheduleOutputDto(startMonth.getMonth(), currentDay, currentWeek, isHoliday,
                        todayWorker));
    }

    private String createDayOffSkipSchedule(DayOffWorker dayOffWorker, Week currentWeek, int currentDay,
                                            List<ScheduleOutputDto> scheduleOutputDtos) {
        dayOffWorker.plusCurrentIndex();
        String todayWorker = dayOffWorker.getWorkers().get(dayOffWorker.currentIndex);
        dayOffWorker.setSkip();
        dayOffWorker.minusCurrentIndex();
        createOutputDto(scheduleOutputDtos, currentDay, currentWeek, false, todayWorker);
        return todayWorker;
    }
}
