package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.enums.ServiceMessages;

public class InputView {
    public String readDate() {
        System.out.println(ServiceMessages.VISTED_DAY.getMessage());
        return Console.readLine();
    }

    public String readMenu() {
        System.out.println(ServiceMessages.ORDER_MENU.getMessage());
        return Console.readLine();
    }
}
