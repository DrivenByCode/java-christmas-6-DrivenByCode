package christmas.dto;

public class OrderSummary {
    private final int dailyDiscount;
    private final int weekdayDessertDiscount;
    private final int weekendMainDiscount;
    private final int specialDiscount;
    private final int totalDiscount;
    private final int finalPrice;
    private final String eventBadge;
    private final boolean champagneApplicable;
    private final boolean discountApplicable;

    public OrderSummary(int dailyDiscount, int weekdayDessertDiscount, int weekendMainDiscount,
                        int specialDiscount, int totalDiscount, int finalPrice,
                        String eventBadge, boolean champagneApplicable, boolean discountApplicable) {
        this.dailyDiscount = dailyDiscount;
        this.weekdayDessertDiscount = weekdayDessertDiscount;
        this.weekendMainDiscount = weekendMainDiscount;
        this.specialDiscount = specialDiscount;
        this.totalDiscount = totalDiscount;
        this.finalPrice = finalPrice;
        this.eventBadge = eventBadge;
        this.champagneApplicable = champagneApplicable;
        this.discountApplicable = discountApplicable;
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

    public int getFinalPrice() {
        return finalPrice;
    }

    public String getEventBadge() {
        return eventBadge;
    }

    public boolean isChampagneApplicable() {
        return champagneApplicable;
    }

    public boolean isDiscountApplicable() {
        return discountApplicable;
    }
}

