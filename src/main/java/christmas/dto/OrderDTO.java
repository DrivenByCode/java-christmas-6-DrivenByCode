package christmas.dto;

import christmas.model.OrderInfo;
import java.util.List;

public class OrderDTO {
    private final List<OrderInfo> orderItems;
    private final int totalPrice;

    public OrderDTO(final List<OrderInfo> orderItems, final int totalPrice) {
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public List<OrderInfo> getOrderItems() {
        return orderItems;
    }


    public int getTotalPrice() {
        return totalPrice;
    }

}
