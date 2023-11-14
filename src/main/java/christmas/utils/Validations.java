package christmas.utils;

import christmas.common.CustomException;
import christmas.common.enums.ErrorMessages;
import christmas.common.enums.Menu;
import christmas.model.OrderItem;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Validations {
    private static void validateItem(OrderItem item) {
        if (!Menu.hasName(item.getName())) {
            throw new CustomException();
        }

        if (item.getQuantity() < 1) {
            throw new CustomException();
        }
    }

    public void isDateNumber(final String input) {
        if (!input.matches("[0-9]+")) {
            throw new CustomException(ErrorMessages.INVALID_DATE.getMessage());
        }
    }

    public void isDateInRange(String input) {
        int date = Integer.parseInt(input);
        if (0 >= date || 31 < date) {
            throw new CustomException(ErrorMessages.INVALID_DATE.getMessage());
        }
    }

    public void hasMenuValidCharacters(final String input) {
        String[] items = input.split(",");
        for (String item : items) {
            if (!item.matches("[가-힣]+-\\d+")) {
                throw new CustomException(ErrorMessages.INVALID_ORDER.getMessage());
            }
        }
    }

    public void validateOrder(final List<OrderItem> orderItems) {
        if (orderItems.stream().mapToInt(OrderItem::getQuantity).sum() > 20) {
            throw new CustomException(ErrorMessages.ORDER_QUANTITY.getMessage());
        }

        Set<String> uniqueMenuNames = orderItems.stream()
                .map(OrderItem::getName)
                .collect(Collectors.toSet());
        if (uniqueMenuNames.size() != orderItems.size()) {
            throw new CustomException(ErrorMessages.INVALID_ORDER.getMessage());
        }

        if (orderItems.stream().allMatch(item -> Menu.isBeverage(item.getName()))) {
            throw new CustomException(ErrorMessages.ONLY_BEVERAGE.getMessage());
        }

        for (OrderItem item : orderItems) {
            validateItem(item);
        }
    }
}
