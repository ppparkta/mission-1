package oncall;


import camp.nextstep.edu.missionutils.Console;
import oncall.controller.WorkScheduleController;
import oncall.util.InputManager;

public class Application {

    public static void main(String[] args) {
        try {
            InputManager inputManager = new InputManager();
            WorkScheduleController workScheduleController = new WorkScheduleController(inputManager);
            workScheduleController.run();
        }
        finally {
            Console.close();
        }
    }
}
