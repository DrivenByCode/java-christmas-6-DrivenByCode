package christmas.service;

import christmas.common.DiscountConstants;
import christmas.dto.DateDTO;
import christmas.model.DiscountInfo;

public class DateService {

    public DiscountInfo calculateDiscounts(DateDTO dateDTO) {
        int dayOfMonth = dateDTO.getDate();
        int dayOfTheWeek = dateDTO.getDayOfTheWeek();
        boolean isStarDay = checkIfStarDay(dayOfMonth);

        int dailyDiscount = calculateDailyDiscount(dayOfMonth);
        int starDiscount = 0;
        if (isStarDay) {
            starDiscount = DiscountConstants.SPECIAL_DISCOUNT;
        }

        int[] discounts = {dailyDiscount, starDiscount};

        boolean isWeekendMainDiscount = isWeekend(dayOfTheWeek);
        boolean isWeekdayDessertDiscount = isWeekday(dayOfTheWeek);

        return new DiscountInfo(discounts, new boolean[]{isWeekendMainDiscount, isWeekdayDessertDiscount});
    }

    private int calculateDailyDiscount(int dayOfMonth) {
        if (dayOfMonth >= 1 && dayOfMonth <= DiscountConstants.EVENT_END) {
            return 1_000 + (dayOfMonth - 1) * 100;
        }
        return 0;
    }

    private boolean isWeekday(int dayOfTheWeek) {
        return dayOfTheWeek >= 0 && dayOfTheWeek <= 4;
    }

    private boolean isWeekend(int dayOfTheWeek) {
        return dayOfTheWeek == 5 || dayOfTheWeek == 6;
    }

    private boolean checkIfStarDay(int dayOfMonth) {
        return DiscountConstants.SPECIAL_DAYS.contains(dayOfMonth);
    }
}
