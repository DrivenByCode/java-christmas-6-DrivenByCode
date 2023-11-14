package christmas.utils;

import java.text.DecimalFormat;

public class MoneyFormatter {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public static String format(int money) {
        return decimalFormat.format(money);
    }
}
