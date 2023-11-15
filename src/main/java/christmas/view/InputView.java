package christmas.view;

import christmas.utils.Validations;

public class InputView {
    private static final Validations validations = new Validations();
    private final InputProvider inputProvider;

    public InputView(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public String readDate() {
        String input = inputProvider.readLine();
        validations.validateDate(input);
        return input;
    }

    public String readMenu() {
        String input = inputProvider.readLine();
        validations.validateOrderInput(input);
        return input;
    }
}
