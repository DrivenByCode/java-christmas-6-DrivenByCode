package christmas.model;

public class DiscountInfo {
    private final int[] discounts;
    private final boolean[] discountTypes;

    public DiscountInfo(int[] discounts, boolean[] discountTypes) {
        this.discounts = discounts;
        this.discountTypes = discountTypes;
    }

    public int getDailyDiscount() {
        return discounts[0];
    }

    public int getStarDiscount() {
        return discounts[1];
    }

    public boolean isWeekendMainDiscount() {
        return discountTypes[0];
    }

    public boolean isWeekdayDessertDiscount() {
        return discountTypes[1];
    }

}

