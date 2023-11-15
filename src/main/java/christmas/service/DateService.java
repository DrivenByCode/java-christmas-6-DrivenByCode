package christmas.service;

import christmas.common.DiscountConstants;
import christmas.common.enums.Menu;
import christmas.dto.DateDTO;
import christmas.model.DiscountInfo;
import christmas.model.OrderInfo;
import java.util.List;

public class DateService {


    public DiscountInfo calculateDiscounts(DateDTO dateDTO, int totalOrderAmount, List<OrderInfo> orderItems) {
        int dayOfMonth = dateDTO.getDate();
        int dayOfTheWeek = dateDTO.getDayOfTheWeek();
        boolean isSpecialDay = checkIfSpecialDay(dayOfMonth);

        int dailyDiscount = calculateDailyDiscount(dayOfMonth);
        int weekdayDessertDiscount = calculateWeekdayDessertDiscount(dayOfTheWeek, orderItems);
        int weekendMainDiscount = calculateWeekendMainDiscount(dayOfTheWeek, orderItems);
        int specialDiscount = isSpecialDay ? DiscountConstants.SPECIAL_DISCOUNT : 0;

        boolean isChampagneApplicable = totalOrderAmount >= DiscountConstants.CHAMPAGNE_THRESHOLD;

        return new DiscountInfo(dailyDiscount, weekdayDessertDiscount, weekendMainDiscount, specialDiscount,
                isChampagneApplicable);
    }

    private int calculateDailyDiscount(int dayOfMonth) {
        if (dayOfMonth >= 1 && dayOfMonth <= DiscountConstants.EVENT_END) {
            return 1000 + (dayOfMonth - 1) * 100;
        }
        return 0;
    }

    private int calculateWeekdayDessertDiscount(int dayOfTheWeek, List<OrderInfo> orderItems) {
        if (isWeekday(dayOfTheWeek)) {
            return countDessertItems(orderItems) * DiscountConstants.DESSERT_DISCOUNT_WEEKDAY;
        }
        return 0;
    }

    private int calculateWeekendMainDiscount(int dayOfTheWeek, List<OrderInfo> orderItems) {
        if (isWeekend(dayOfTheWeek)) {
            return countMainItems(orderItems) * DiscountConstants.MAIN_DISCOUNT_WEEKEND;
        }
        return 0;
    }

    private boolean isWeekday(int dayOfTheWeek) {
        return dayOfTheWeek >= 0 && dayOfTheWeek <= 3;
    }

    private boolean isWeekend(int dayOfTheWeek) {
        return dayOfTheWeek == 4 || dayOfTheWeek == 5;
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

    private boolean checkIfSpecialDay(int dayOfMonth) {
        return DiscountConstants.SPECIAL_DAYS.contains(dayOfMonth);
    }

}
