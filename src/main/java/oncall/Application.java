package oncall;


import oncall.controller.WorkScheduleController;
import oncall.util.InputManager;

public class Application {

    public static void main(String[] args) {
        InputManager inputManager = new InputManager();
        WorkScheduleController workScheduleController = new WorkScheduleController(inputManager);
        workScheduleController.run();
    }
}
