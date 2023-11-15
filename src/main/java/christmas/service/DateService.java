package christmas.service;

import christmas.common.DiscountConstants;
import christmas.dto.DateDTO;
import christmas.model.DiscountInfo;

public class DateService {

    public DiscountInfo calculateDiscounts(DateDTO dateDTO) {
        int dayOfMonth = dateDTO.getDate();
        int dayOfWeek = dateDTO.getDayOfTheWeek();
        boolean isSpecialDay = checkIfSpecialDay(dayOfMonth);

        int dailyDiscount = calculateDailyDiscount(dayOfMonth);
        int specialDiscount = 0;
        if (isSpecialDay) {
            specialDiscount = DiscountConstants.SPECIAL_DISCOUNT;
        }

        int[] discounts = {dailyDiscount, specialDiscount};

        boolean isWeekendMainDiscount = isWeekend(dayOfWeek);
        boolean isWeekdayDessertDiscount = isWeekday(dayOfWeek);

        return new DiscountInfo(discounts, new boolean[]{isWeekendMainDiscount, isWeekdayDessertDiscount});
    }

    private int calculateDailyDiscount(int dayOfMonth) {
        if (dayOfMonth >= 1 && dayOfMonth <= DiscountConstants.EVENT_END) {
            return 1_000 + (dayOfMonth - 1) * 100;
        }
        return 0;
    }

    private boolean isWeekday(int dayOfTheWeek) {
        return dayOfTheWeek >= 0 && dayOfTheWeek <= 3;
    }

    private boolean isWeekend(int dayOfTheWeek) {
        return dayOfTheWeek == 4 || dayOfTheWeek == 5;
    }

    private boolean checkIfSpecialDay(int dayOfMonth) {
        return DiscountConstants.SPECIAL_DAYS.contains(dayOfMonth);
    }
}
