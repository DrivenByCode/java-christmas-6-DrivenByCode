package christmas.view;

import christmas.dto.DateDTO;
import christmas.dto.OrderDTO;
import christmas.dto.OrderSummary;
import christmas.utils.Validations;

public class ViewManager {
    private final InputView inputView;
    private final OutputView outputView;
    private final Validations validations;

    public ViewManager(final InputView inputView, final OutputView outputView, final Validations validations) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validations = validations;
    }

    // 예시: 사용자 입력을 받는 메소드
    public String getDate() {
        return inputView.readDate();
    }

    public String getOrders() {
        return inputView.readMenu();
    }

    public void displayOutput(DateDTO dateDTO, OrderDTO orderDTO, OrderSummary orderSummary) {
        outputView.displayOrderSummary(dateDTO, orderDTO, orderSummary);
    }

    public void validateDate(String input) {
        validations.validateDate(input);
    }

    public void validateOrder(String input) {
        validations.validateOrderInput(input);
    }

}

