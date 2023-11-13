package christmas;

import christmas.controller.ChristmasController;
import christmas.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        ChristmasController christmasController = new ChristmasController(inputView);
        christmasController.run();
    }
}
