package oncall.view;

import java.util.List;
import oncall.dto.ScheduleOutputDto;

public class OutputView {
    public static void printScheduleDtos(List<ScheduleOutputDto> scheduleOutputDtos) {
        for (ScheduleOutputDto scheduleOutputDto : scheduleOutputDtos) {
            printScheduleDto(scheduleOutputDto);
        }
    }

    private static void printScheduleDto(ScheduleOutputDto scheduleOutputDto) {
        if (scheduleOutputDto.isHoliday()) {
            System.out.println(String.format("%d월 %d일 %s(휴일) %s", scheduleOutputDto.month(), scheduleOutputDto.day(),
                    scheduleOutputDto.week().getWeek(), scheduleOutputDto.workerName()));
            return;
        }
        System.out.println(String.format("%d월 %d일 %s %s", scheduleOutputDto.month(), scheduleOutputDto.day(),
                scheduleOutputDto.week().getWeek(), scheduleOutputDto.workerName()));
    }

}
