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
import christmas.view.ViewManager;
import java.util.List;

public class ChristmasController {
    private final DateService dateService;
    private final OrderService orderService;
    private final ViewManager viewManager;

    private DateDTO dateDTO;
    private OrderDTO orderDTO;

    public ChristmasController(final ViewManager viewManager, final DateService dateService,
                               final OrderService orderService) {
        System.out.println(ServiceMessages.FIRST_MSG.getMessage());
        this.viewManager = viewManager;
        this.dateService = dateService;
        this.orderService = orderService;
    }

    public void run() {
        this.dateDTO = processReservationDate();
        this.orderDTO = processOrderInput();
        processDiscounts(dateDTO, orderDTO);
    }

    private DateDTO processReservationDate() {
        while (true) {
            try {
                String input = viewManager.getDate();
                viewManager.validateDate(input);
                return DateInfo.createDateDTO(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private OrderDTO processOrderInput() {
        while (true) {
            try {
                String input = viewManager.getOrders();
                viewManager.validateOrder(input);
                List<OrderInfo> orderItems = OrderParser.parseOrder(input);
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
        viewManager.displayOutput(dateDTO, orderDTO, orderSummary);
    }
}
