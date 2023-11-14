package christmas.dto;

import christmas.model.OrderInfo;
import java.util.List;

public class OrderDTO {
    private List<OrderInfo> orderItems;
    private int totalPrice;

    public OrderDTO(final List<OrderInfo> orderItems, int totalPrice) {
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public List<OrderInfo> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(final List<OrderInfo> orderItems) {
        this.orderItems = orderItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(final int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
