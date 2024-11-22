package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String getValue() {
        return Console.readLine().replace(" ", "");
    }
}
