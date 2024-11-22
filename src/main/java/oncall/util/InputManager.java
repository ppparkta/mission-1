package oncall.util;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.Date;
import oncall.domain.WorkPermutation;
import oncall.domain.WorkType;
import oncall.domain.Worker;

import java.util.ArrayList;
import java.util.List;

public class InputManager {
    // 시작일 추출 1,금 -> Date
    public Date readStartDate() {
        while (true) {
            try {
                System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요>");
                String inputStartDate = Console.readLine().trim();
                validateDateRead(inputStartDate);
                return dateParser(inputStartDate);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateDateRead(String inputStartDate) {
        try {
            dateParser(inputStartDate);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private Date dateParser(String inputStartDate) {
        String[] inputStrings = inputStartDate.split(",");
        String inputMonth = inputStrings[0].trim();
        String inputDay = inputStrings[1].trim();
        int month = Integer.parseInt(inputMonth);
        String day = inputDay;
        if (month <= 0) {throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");}
        return Date.asDate(month, day);
    }

    // 평일 순서 및 휴일 순서 추출 준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리 -> WorkPermutation
    public List<WorkPermutation> readWorkPermutations() {
        while (true) {
            System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
            String inputWeekWorkPermutations = Console.readLine().trim();
            System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
            String inputHolidayWorkPermutations = Console.readLine().trim();
            try {
                List<Worker> weekWorkers = createWorkers(inputWeekWorkPermutations);
                List<Worker> holidayWorkers = createWorkers(inputHolidayWorkPermutations);
                List<WorkPermutation> workPermutations = new ArrayList<>();
                workPermutations.add(WorkPermutation.of(weekWorkers,WorkType.WEEKDAYWORKER));
                workPermutations.add(WorkPermutation.of(holidayWorkers,WorkType.HOLIDAYWORKER));
                return workPermutations;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Worker> createWorkers(String inputWorkPermutations) {
        String[] inputStrings = inputWorkPermutations.split(",");
        // 근무자 생성
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < inputStrings.length; i++) {
            inputStrings[i] = inputStrings[i].trim();
            boolean isNotWorkerDuplcate = Worker.checkDuplicateName(workers,inputStrings[i]);
            if (isNotWorkerDuplcate == false) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
            }
            workers.add(new Worker(inputStrings[i]));
        }
        return workers;
    }







}
