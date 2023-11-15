package christmas.utils;

import java.text.DecimalFormat;

public class MoneyFormatter {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private static final String currencyType = "원";

    public static String format(int money) {
        return decimalFormat.format(money) + currencyType;
    }
}
