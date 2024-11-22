package oncall;


import oncall.util.InputManager;

public class Application {

    public static void main(String[] args) {
        InputManager inputManager = new InputManager();
        inputManager.readStartDate();
        inputManager.readWorkPermutations();
    }
}
