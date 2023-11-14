package christmas.dto;

import christmas.model.OrderItem;
import java.util.List;

public class OrderDTO {
    private List<OrderItem> orderItems;
    private int totalPrice;

    public OrderDTO(final List<OrderItem> orderItems, int totalPrice) {
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(final List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(final int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
