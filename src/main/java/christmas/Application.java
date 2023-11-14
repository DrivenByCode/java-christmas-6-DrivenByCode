package christmas;

import christmas.controller.ChristmasController;
import christmas.utils.Validations;
import christmas.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        final InputView inputView = new InputView();
        final Validations validations = new Validations();
        ChristmasController christmasController = new ChristmasController(inputView, validations);
        christmasController.run();
    }
}
