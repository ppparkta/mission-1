package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String getValue(String prompt) {
        System.out.print(prompt);
        return Console.readLine().replace(" ", "");
    }
}
