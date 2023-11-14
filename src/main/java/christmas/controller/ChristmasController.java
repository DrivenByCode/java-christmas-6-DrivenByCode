package christmas.controller;

import christmas.common.enums.ServiceMessages;
import christmas.dto.DateDTO;
import christmas.dto.OrderDTO;
import christmas.dto.OrderSummary;
import christmas.model.DateInfo;
import christmas.model.DiscountInfo;
import christmas.model.OrderInfo;
import christmas.model.OrderModel;
import christmas.service.DateService;
import christmas.service.OrderService;
import christmas.utils.OrderParser;
import christmas.utils.Validations;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {
    private final InputView inputView;
    private final Validations validations;
    private final DateService dateService;
    private final OrderService orderService;
    private final OutputView outputView;

    private DateDTO dateDTO;
    private OrderDTO orderDTO;

    public ChristmasController(final InputView inputView, final Validations validations,
                               final DateService dateService, final OrderService orderService,
                               final OutputView outputView) {
        System.out.println(ServiceMessages.FIRST_MSG.getMessage());
        this.inputView = inputView;
        this.validations = validations;
        this.dateService = dateService;
        this.orderService = orderService;
        this.outputView = outputView;
    }

    public void run() {
        this.dateDTO = processReservationDate();
        this.orderDTO = processOrderInput();
        processDiscounts(dateDTO, orderDTO);
    }

    private DateDTO processReservationDate() {
        while (true) {
            try {
                String input = inputView.readDate();
                validations.isDateNumber(input);
                validations.isDateInRange(input);
                DateDTO dateDTO = DateInfo.createDateDTO(input);
                return dateDTO;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private OrderDTO processOrderInput() {
        while (true) {
            try {
                String input = inputView.readMenu();
                validations.hasMenuValidCharacters(input);
                List<OrderInfo> orderItems = OrderParser.parseOrder(input);
                validations.validateOrder(orderItems);
                return OrderModel.createOrderDTO(orderItems);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void processDiscounts(DateDTO dateDTO, OrderDTO orderDTO) {
        int totalOrderAmount = orderDTO.getTotalPrice();
        DiscountInfo discountInfo = dateService.calculateDiscounts(dateDTO, totalOrderAmount, orderDTO.getOrderItems());
        OrderSummary orderSummary = orderService.calculateOrderSummary(orderDTO, discountInfo);
        outputView.displayOrderSummary(dateDTO, orderDTO, orderSummary);
    }

}
