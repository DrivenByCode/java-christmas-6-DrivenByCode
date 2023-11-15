package christmas.utils;

import christmas.common.CustomException;
import christmas.common.enums.ErrorMessages;
import christmas.common.enums.Menu;
import christmas.model.OrderInfo;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Validations {

    private static void validateItem(final OrderInfo item) {
        if ((!Menu.hasName(item.getName())) || item.getQuantity() < 1) {
            throw new CustomException();
        }
    }

    public void validateDate(final String input) {
        isDateNumber(input);
        isDateInRange(input);
    }

    private void isDateNumber(final String input) {
        if (!input.matches("[0-9]+")) {
            throw new CustomException(ErrorMessages.INVALID_DATE);
        }
    }

    private void isDateInRange(final String input) {
        int date = Integer.parseInt(input);
        if (0 >= date || 31 < date) {
            throw new CustomException(ErrorMessages.INVALID_DATE);
        }
    }

    public void validateOrderInput(final String input) {
        hasMenuValidCharacters(input);
        List<OrderInfo> orderItems = OrderParser.parseOrder(input);
        validateOrder(orderItems);
    }

    private void hasMenuValidCharacters(final String input) {
        String[] items = input.split(",");
        for (String item : items) {
            if (!item.matches("[가-힣]+-\\d+")) {
                throw new CustomException();
            }
        }
    }

    public void validateOrder(final List<OrderInfo> orderItems) {
        validateOrderQuantity(orderItems);
        validateUniqueMenuNames(orderItems);
        validateNotOnlyBeverages(orderItems);
        validateEachItem(orderItems);
    }

    private void validateOrderQuantity(final List<OrderInfo> orderItems) {
        if (orderItems.stream().mapToInt(OrderInfo::getQuantity).sum() > 20) {
            throw new CustomException(ErrorMessages.ORDER_QUANTITY);
        }
    }

    private void validateUniqueMenuNames(final List<OrderInfo> orderItems) {
        Set<String> uniqueMenuNames = orderItems.stream()
                .map(OrderInfo::getName)
                .collect(Collectors.toSet());
        if (uniqueMenuNames.size() != orderItems.size()) {
            throw new CustomException();
        }
    }

    private void validateNotOnlyBeverages(final List<OrderInfo> orderItems) {
        if (orderItems.stream().allMatch(item -> Menu.isBeverage(item.getName()))) {
            throw new CustomException(ErrorMessages.ONLY_BEVERAGE);
        }
    }

    private void validateEachItem(final List<OrderInfo> orderItems) {
        for (OrderInfo item : orderItems) {
            validateItem(item);
        }
    }
}
