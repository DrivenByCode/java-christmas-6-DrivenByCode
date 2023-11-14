package christmas.model;

import christmas.dto.OrderDTO;
import christmas.common.enums.Menu;
import java.util.List;

public class OrderModel {
    public static OrderDTO createOrderDTO(List<OrderItem> orderItems) {
        int totalPrice = orderItems.stream()
                .mapToInt(item -> Menu.getPriceByName(item.getName()) * item.getQuantity())
                .sum();
        return new OrderDTO(orderItems, totalPrice);
    }
}

