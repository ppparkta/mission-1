package oncall.controller;

import java.util.List;
import oncall.constant.WorkerConfig;
import oncall.dto.ScheduleInputDto;
import oncall.dto.WorkerInputDto;
import oncall.model.DayOffWorker;
import oncall.model.Schedule;
import oncall.model.WeekWorker;
import oncall.model.Worker;
import oncall.view.InputView;

public class WorkController {
    private final InputHandler inputHandler;

    public WorkController() {
        InputView inputView = new InputView();
        this.inputHandler = new InputHandler(inputView);
    }

    public void run() {
        ScheduleInputDto scheduleInputDto = inputHandler.getSchedule();
        Schedule schedule = new Schedule(scheduleInputDto.month(), scheduleInputDto.week());

        List<WorkerInputDto> workersInputDtos = inputHandler.getWorker();
        WeekWorker weekWorker = new WeekWorker(workersInputDtos.get(WorkerConfig.WEEK.getValue()).members());
        DayOffWorker dayOffWorker = new DayOffWorker(workersInputDtos.get(WorkerConfig.DAY_OFF.getValue()).members());

        // 출력
    }
}
