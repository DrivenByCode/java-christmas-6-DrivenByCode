package christmas.controller;

import christmas.dto.DateDTO;
import christmas.dto.OrderDTO;
import christmas.model.DateModel;
import christmas.model.OrderItem;
import christmas.model.OrderModel;
import christmas.utils.OrderParser;
import christmas.utils.Validations;
import christmas.common.enums.ServiceMessages;
import christmas.view.InputView;
import java.util.List;

public class ChristmasController {
    private final InputView inputView;
    private final Validations validations;
    private DateDTO dateDTO;
    private OrderDTO orderDTO;

    public ChristmasController(final InputView inputView, final Validations validations) {
        System.out.println(ServiceMessages.FIRST_MSG.getMessage());
        this.inputView = inputView;
        this.validations = validations;
    }

    public void run() {
        this.dateDTO = processReservationDate();
        this.orderDTO = processOrderInput();
    }

    private DateDTO processReservationDate() {
        while(true) {
            try {
                String input = inputView.readDate();
                validations.isDateNumber(input);
                validations.isDateInRange(input);
                return DateModel.createDateDTO(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private OrderDTO processOrderInput() {
        while(true) {
            try {
                String input = inputView.readMenu();
                validations.hasMenuValidCharacters(input);
                List<OrderItem> orderItems = OrderParser.parseOrder(input);
                validations.validateOrder(orderItems);
                return OrderModel.createOrderDTO(orderItems);
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
