package oncall.controller;

import java.util.List;
import oncall.constant.WorkerConfig;
import oncall.dto.ScheduleInputDto;
import oncall.dto.ScheduleOutputDto;
import oncall.dto.WorkerInputDto;
import oncall.model.DayOffWorker;
import oncall.model.Schedule;
import oncall.model.WeekWorker;
import oncall.view.InputView;
import oncall.view.OutputView;

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

        List<ScheduleOutputDto> scheduleOutputDtos = schedule.generate(weekWorker, dayOffWorker);

        OutputView.printScheduleDtos(scheduleOutputDtos);
    }
}
