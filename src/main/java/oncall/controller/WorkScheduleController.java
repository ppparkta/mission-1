package oncall.controller;

import oncall.domain.Date;
import oncall.domain.Day;
import oncall.domain.WorkPermutation;
import oncall.util.InputManager;

import java.util.List;

public class WorkScheduleController {
    private final InputManager inputManager;

    public WorkScheduleController(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void run() {
        // 시작일, 평일 순열, 공휴일 순열
        Date startDate = inputManager.readStartDate();
        List<WorkPermutation> workPermutationWeekAndHoliday = inputManager.readWorkPermutations();
        WorkPermutation weekPermutation = workPermutationWeekAndHoliday.get(0);
        WorkPermutation holidayPermutation = workPermutationWeekAndHoliday.get(1);
        calculateWorkSchedule(startDate, weekPermutation, holidayPermutation);
    }
    // 모든 요일 순회 및  관장
    private void calculateWorkSchedule(Date startDate, WorkPermutation weekPermutation, WorkPermutation holidayPermutation) {
        int lastDay = startDate.getLastDay();
        int weekTurnPoint = 0;
        int holidayTurnPoint = 0;
        List<Day> days = Day.getDays(); // 요일 리스트
        int dayPoint = days.indexOf(startDate.getDay()); // 시작 요일 지점
        String yesterDayWorkername = "";
        // 일 별 절차
        for (int i = 1; i <= lastDay; i++) {
            Date pointDate = new Date(startDate.getMonth(), days.get(dayPoint)); // 해당 일 정보 생성
            boolean isHoliday = pointDate.isHoliday(i);
            if (isHoliday) {
                String workerName = holidayPermutation.getWorkers().get(holidayTurnPoint).getName();
                holidayTurnPoint++;
                System.out.println(pointDate.getMonth()+ "월 " + i +"일 " + Day.getDayString(dayPoint) + " " + workerName);
                if (holidayPermutation.getWorkers().size()-1 == holidayTurnPoint) {holidayTurnPoint = 0;}
                dayPoint ++;
                if (dayPoint == 7) {dayPoint = 0;}

            }
            if (!isHoliday) {
                String workerName = weekPermutation.getWorkers().get(weekTurnPoint).getName();
                weekTurnPoint++;
                System.out.println(pointDate.getMonth()+ "월 " + i +"일 " + Day.getDayString(dayPoint) + " " + workerName);
                if (weekPermutation.getWorkers().size()-1 == weekTurnPoint) {weekTurnPoint = 0;}
                dayPoint ++;
                if (dayPoint == 7) {dayPoint = 0;}
            }

        }
    }



}
