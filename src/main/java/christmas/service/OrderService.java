package christmas.service;

import christmas.common.DiscountConstants;
import christmas.common.enums.EventBadge;
import christmas.common.enums.Menu;
import christmas.dto.OrderDTO;
import christmas.dto.OrderSummary;
import christmas.model.DiscountInfo;
import christmas.model.OrderInfo;
import java.util.List;

public class OrderService {

    public OrderSummary calculateOrderSummary(OrderDTO orderDTO, DiscountInfo discountInfo) {
        int totalPrice = orderDTO.getTotalPrice();
        boolean isEligibleForChampagne = totalPrice >= DiscountConstants.CHAMPAGNE_THRESHOLD;

        if (totalPrice < DiscountConstants.DISCOUNT_THRESHOLD) {
            return new OrderSummary(totalPrice, 0, 0,
                    0, 0, 0, totalPrice, "없음",
                    isEligibleForChampagne);
        }

        int weekdayDessertDiscount = 0;
        if (discountInfo.isWeekdayDessertDiscount()) {
            weekdayDessertDiscount = calculateWeekdayDessertDiscount(orderDTO.getOrderItems());
        }
        int weekendMainDiscount = 0;
        if (discountInfo.isWeekendMainDiscount()) {
            weekendMainDiscount = calculateWeekendMainDiscount(orderDTO.getOrderItems());
        }

        int totalDiscount = discountInfo.getDailyDiscount() +
                weekdayDessertDiscount +
                weekendMainDiscount +
                discountInfo.getSpecialDiscount();
        if (isEligibleForChampagne) {
            totalDiscount += 25_000;
        }

        int finalPrice = totalPrice - totalDiscount;
        if (isEligibleForChampagne) {
            finalPrice += 25_000;
        }

        String eventBadge = determineEventBadge(totalDiscount);

        return new OrderSummary(totalPrice, discountInfo.getDailyDiscount(), weekdayDessertDiscount,
                weekendMainDiscount, discountInfo.getSpecialDiscount(), totalDiscount, finalPrice, eventBadge,
                isEligibleForChampagne);
    }

    private int calculateWeekdayDessertDiscount(List<OrderInfo> orderItems) {
        return countDessertItems(orderItems) * DiscountConstants.DESSERT_DISCOUNT_WEEKDAY;
    }

    private int calculateWeekendMainDiscount(List<OrderInfo> orderItems) {
        return countMainItems(orderItems) * DiscountConstants.MAIN_DISCOUNT_WEEKEND;
    }

    private int countDessertItems(List<OrderInfo> orderItems) {
        return orderItems.stream()
                .filter(item -> Menu.isDessert(item.getName()))
                .mapToInt(OrderInfo::getQuantity)
                .sum();
    }

    private int countMainItems(List<OrderInfo> orderItems) {
        return orderItems.stream()
                .filter(item -> Menu.isMainDish(item.getName()))
                .mapToInt(OrderInfo::getQuantity)
                .sum();
    }


    private String determineEventBadge(int totalDiscount) {
        return EventBadge.determineEventBadge(totalDiscount);
    }
}



