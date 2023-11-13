package christmas.controller;

import christmas.dto.DateDTO;
import christmas.service.OrderService;
import christmas.utils.Validations;
import christmas.utils.enums.ServiceMessages;
import christmas.view.InputView;
import java.util.Map;

public class ChristmasController {
    private final InputView inputView;
    private final Validations validations = new Validations();
    private final OrderService menuService = new OrderService();

    public ChristmasController(final InputView inputView) {
        System.out.println(ServiceMessages.FIRST_MSG.getMessage());
        this.inputView = inputView;
    }

    public void run() {
        this.createDateDTO();
        this.createMenuDTO();
    }

    private String createDateDTO() {
        while(true) {
            try {
                String input = inputView.readDate();
                validations.isDateNumber(input);
                validations.isDateInRange(input);
                DateDTO dateDTO = new DateDTO(input);
                return dateDTO.getDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Map<String, Integer> createMenuDTO() {
        Map<String, Integer> menu = menuService.processOrderInput();
        return menu;
    }
}
