package christmas;

import christmas.controller.ChristmasController;
import christmas.service.DateService;
import christmas.service.OrderService;
import christmas.utils.Validations;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        final InputView inputView = new InputView();
        final Validations validations = new Validations();
        final DateService dateService = new DateService();
        final OrderService orderService = new OrderService();
        final OutputView outputView = new OutputView();
        ChristmasController christmasController = new ChristmasController(inputView, validations, dateService,
                orderService, outputView);
        christmasController.run();
    }
}
