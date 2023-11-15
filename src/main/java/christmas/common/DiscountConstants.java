package christmas.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DiscountConstants {
    public static final int EVENT_END = 25;
    public static final int DESSERT_DISCOUNT_WEEKDAY = 2_023;
    public static final int MAIN_DISCOUNT_WEEKEND = 2_023;
    public static final int SPECIAL_DISCOUNT = 1_000;
    public static final int CHAMPAGNE_THRESHOLD = 120_000;
    public static final int DISCOUNT_THRESHOLD = 10_000;
    public static final Set<Integer> SPECIAL_DAYS = new HashSet<>(Arrays.asList(3, 10, 17, 24, 25, 31));

}
