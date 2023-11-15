package christmas.model;

public class OrderInfo {
    private final String name;
    private final int quantity;

    public OrderInfo(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}

