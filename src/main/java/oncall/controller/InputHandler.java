package oncall.controller;

import java.util.ArrayList;
import java.util.List;
import oncall.constant.ExceptionMessage;
import oncall.constant.WorkerConfig;
import oncall.dto.ScheduleInputDto;
import oncall.dto.WorkerInputDto;
import oncall.utils.InputParser;
import oncall.view.InputView;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public ScheduleInputDto getSchedule() {
        while (true) {
            try {
                System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
                String inputValue = inputView.getValue();
                return InputParser.parseSchedule(inputValue);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<WorkerInputDto> getWorker() {
        while (true) {
            try {
                List<WorkerInputDto> workerInputDtos = new ArrayList<>();

                System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
                String inputValue = inputView.getValue();
                workerInputDtos.add(InputParser.parseWorker(inputValue));

                System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
                inputValue = inputView.getValue();
                workerInputDtos.add(InputParser.parseWorker(inputValue));

                validateWorkerInput(workerInputDtos.get(WorkerConfig.WEEK.getValue()),
                        workerInputDtos.get(WorkerConfig.DAY_OFF.getValue()));
                return workerInputDtos;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateWorkerInput(WorkerInputDto weekWorkerInputDto, WorkerInputDto dayOffWorkerInputDto) {
        // 두 배열의 크기가 같은지 확인
        if (weekWorkerInputDto.getSize() != dayOffWorkerInputDto.getSize()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
        // 평일 근무자가 모두 휴일 근무에 있는지 확인
        for (String weekWorker : weekWorkerInputDto.members()) {
            if (!WorkerInputDto.isIncludeDayOff(weekWorker, dayOffWorkerInputDto)) {
                throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
            }
        }
    }
}
