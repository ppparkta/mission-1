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

    private void validate(int startMonth) {
        if (startMonth < 1 || startMonth > 12) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }

    public List<ScheduleOutputDto> generate(WeekWorker weekWorker, DayOffWorker dayOffWorker) {
        // 초기 세팅
        String yesterdayWorker = "";
        String todayWorker;
        Week currentWeek = this.startWeek;
        int currentDay = 1;
        List<ScheduleOutputDto> scheduleOutputDtos = new ArrayList<>();

        // 마지막 날까지 반복
        while (currentDay <= startMonth.getLastDay()) {
            // 휴일이라면

            if (currentWeek.isDayOff()) {
                todayWorker = dayOffWorker.getWorkers().get(dayOffWorker.currentIndex);
                // 연속근무라 스킵할 경우
                if (todayWorker.equals(yesterdayWorker)) {
                    dayOffWorker.plusCurrentIndex();
                    todayWorker = dayOffWorker.getWorkers().get(dayOffWorker.currentIndex);
                    dayOffWorker.setSkip();
                    dayOffWorker.minusCurrentIndex();
                    scheduleOutputDtos.add(
                            new ScheduleOutputDto(startMonth.getMonth(), currentDay, currentWeek, false,
                                    todayWorker));
                } else {
                    dayOffWorker.plusCurrentIndex();
                    scheduleOutputDtos.add(
                            new ScheduleOutputDto(startMonth.getMonth(), currentDay, currentWeek, false,
                                    todayWorker));
                }
            }
            // 평일이라면
            if (!currentWeek.isDayOff()) {
                // 공휴일이라면
                if (HoliDay.isHoliday(startMonth.getMonth(), currentDay)) {
                    todayWorker = dayOffWorker.getWorkers().get(dayOffWorker.currentIndex);
                    if (todayWorker.equals(yesterdayWorker)) {
                        dayOffWorker.plusCurrentIndex();
                        todayWorker = dayOffWorker.getWorkers().get(dayOffWorker.currentIndex);
                        dayOffWorker.setSkip();
                        dayOffWorker.minusCurrentIndex();
                        scheduleOutputDtos.add(
                                new ScheduleOutputDto(startMonth.getMonth(), currentDay, currentWeek, true,
                                        todayWorker));
                    } else {
                        dayOffWorker.plusCurrentIndex();
                        scheduleOutputDtos.add(
                                new ScheduleOutputDto(startMonth.getMonth(), currentDay, currentWeek, true,
                                        todayWorker));
                    }
                }
                // 공휴일이 아니라면
                else {
                    todayWorker = weekWorker.getWorkers().get(weekWorker.currentIndex);
                    if (todayWorker.equals(yesterdayWorker)) {
                        weekWorker.plusCurrentIndex();
                        todayWorker = weekWorker.getWorkers().get(weekWorker.currentIndex);
                        weekWorker.setSkip();
                        weekWorker.minusCurrentIndex();
                        scheduleOutputDtos.add(
                                new ScheduleOutputDto(startMonth.getMonth(), currentDay, currentWeek, false,
                                        todayWorker));
                    } else {
                        weekWorker.plusCurrentIndex();
                        scheduleOutputDtos.add(
                                new ScheduleOutputDto(startMonth.getMonth(), currentDay, currentWeek, false,
                                        todayWorker));
                    }
                }
                yesterdayWorker = todayWorker;
                currentWeek = Week.nextWeek(currentWeek);
                currentDay++;
            }
        }
        return scheduleOutputDtos;
    }
}
