package oncall.controller;

import java.util.List;
import oncall.dto.ScheduleInputDto;
import oncall.dto.WorkerInputDto;
import oncall.model.Schedule;
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

        // 평일 비상 근무 순번대로 사원 닉네임을 입력하세요> 준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리
        List<WorkerInputDto> workersInputDto = inputHandler.getWorker();

        // 출력
    }
}
