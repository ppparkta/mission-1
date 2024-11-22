package oncall;

import oncall.controller.WorkController;

public class Application {
    public static void main(String[] args) {
        WorkController workController = new WorkController();
        workController.run();
    }
}
