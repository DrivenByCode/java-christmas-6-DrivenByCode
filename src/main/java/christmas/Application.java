package christmas;

import christmas.controller.ChristmasController;
import christmas.service.DateService;
import christmas.service.OrderService;
import christmas.utils.Validations;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.ViewManager;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        final ViewManager viewManager = new ViewManager(new InputView(), new OutputView(), new Validations());
        final DateService dateService = new DateService();
        final OrderService orderService = new OrderService();
        ChristmasController christmasController = new ChristmasController(viewManager, dateService,
                orderService);
        christmasController.run();
    }
}
