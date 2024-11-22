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
                String inputValue = inputView.getValue("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
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

                String inputValue = inputView.getValue("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
                workerInputDtos.add(InputParser.parseWorker(inputValue));

                inputValue = inputView.getValue("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
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
        if (weekWorkerInputDto.getSize() != dayOffWorkerInputDto.getSize()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
        for (String weekWorker : weekWorkerInputDto.members()) {
            if (!WorkerInputDto.isIncludeDayOff(weekWorker, dayOffWorkerInputDto)) {
                throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
            }
        }
    }
}
