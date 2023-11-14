package christmas.model;

public class DiscountInfo {
    private final int dailyDiscount;
    private final int weekdayDessertDiscount;
    private final int weekendMainDiscount;
    private final int specialDiscount;
    private final boolean champagneApplicable;

    public DiscountInfo(int dailyDiscount, int weekdayDessertDiscount, int weekendMainDiscount, int specialDiscount,
                        boolean champagneApplicable) {
        this.dailyDiscount = dailyDiscount;
        this.weekdayDessertDiscount = weekdayDessertDiscount;
        this.weekendMainDiscount = weekendMainDiscount;
        this.specialDiscount = specialDiscount;
        this.champagneApplicable = champagneApplicable;
    }

    // 각 필드에 대한 getter 메서드를 제공할 수 있습니다.
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

    public boolean isChampagneApplicable() {
        return champagneApplicable;
    }
}

