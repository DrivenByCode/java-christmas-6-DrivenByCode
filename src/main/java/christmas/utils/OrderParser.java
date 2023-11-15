package christmas.utils;

import christmas.model.OrderInfo;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderParser {
    public static List<OrderInfo> parseOrder(String input) {
        return Arrays.stream(input.split(","))
                .map(item -> {
                    String[] parts = item.split("-");
                    String name = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    return new OrderInfo(name, quantity);
                })
                .collect(Collectors.toList());
    }
}

