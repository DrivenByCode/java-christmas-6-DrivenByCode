package christmas.dto;

import christmas.model.DiscountInfo;

public class OrderSummary {
    private final int totalOrderAmount;
    private final int dailyDiscount;
    private final int weekdayDessertDiscount;
    private final int weekendMainDiscount;
    private final int specialDiscount;
    private final int totalDiscount;
    private final int finalAmount;
    private final String eventBadge;
    private final boolean champagneApplicable;

    public OrderSummary(int totalOrderAmount, DiscountInfo discountInfo, int totalDiscount, int finalAmount,
                        String eventBadge, boolean champagneApplicable) {
        this.totalOrderAmount = totalOrderAmount;
        this.dailyDiscount = discountInfo.getDailyDiscount();
        this.weekdayDessertDiscount = discountInfo.getWeekdayDessertDiscount();
        this.weekendMainDiscount = discountInfo.getWeekendMainDiscount();
        this.specialDiscount = discountInfo.getSpecialDiscount();
        this.totalDiscount = totalDiscount;
        this.finalAmount = finalAmount;
        this.eventBadge = eventBadge;
        this.champagneApplicable = champagneApplicable;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public int getDailyDiscount() {
        return dailyDiscount;
    }

    public int getWeekdayDessertDiscount() {
        return weekdayDessertDiscount;
    }

    public int getWeekendMainDiscount() {
        return weekendMainDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getFinalAmount() {
        return finalAmount;
    }

    public String getEventBadge() {
        return eventBadge;
    }

    public boolean isChampagneApplicable() {
        return champagneApplicable;
    }
}

