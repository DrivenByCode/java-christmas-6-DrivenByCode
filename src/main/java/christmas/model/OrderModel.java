package christmas.model;

import christmas.common.enums.Menu;
import christmas.dto.OrderDTO;
import java.util.List;

public class OrderModel {
    public static OrderDTO createOrderDTO(List<OrderInfo> orderItems) {
        int totalPrice = orderItems.stream()
                .mapToInt(item -> Menu.getPriceByName(item.getName()) * item.getQuantity())
                .sum();
        return new OrderDTO(orderItems, totalPrice);
    }
}

