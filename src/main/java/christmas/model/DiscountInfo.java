package christmas.model;

public class DiscountInfo {
    private final int[] discounts;
    private final boolean[] discountTypes;

    public DiscountInfo(int[] discounts, boolean[] discountTypes) {
        this.discounts = discounts;
        this.discountTypes = discountTypes;
    }

    // 각 필드에 대한 getter 메서드를 제공할 수 있습니다.
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

