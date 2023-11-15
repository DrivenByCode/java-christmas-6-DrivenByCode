package christmas.common.enums;

import christmas.dto.OrderSummary;
import christmas.utils.MoneyFormatter;

public enum DiscountType {
    DAILY_DISCOUNT {
        @Override
        public boolean isApplicable(OrderSummary summary) {
            return summary.getDailyDiscount() > 0;
        }

        @Override
        public String getMessage(OrderSummary summary) {
            return "크리스마스 디데이 할인: -" + MoneyFormatter.format(summary.getDailyDiscount());
        }
    },
    WEEKDAY_DESSERT_DISCOUNT {
        @Override
        public boolean isApplicable(OrderSummary summary) {
            return summary.getWeekdayDessertDiscount() > 0;
        }

        @Override
        public String getMessage(OrderSummary summary) {
            return "평일 할인: -" + MoneyFormatter.format(summary.getWeekdayDessertDiscount());
        }
    },
    SPECIAL_DISCOUNT {
        @Override
        public boolean isApplicable(OrderSummary summary) {
            return summary.getSpecialDiscount() > 0;
        }

        @Override
        public String getMessage(OrderSummary summary) {
            return "특별 할인: -" + MoneyFormatter.format(summary.getSpecialDiscount());
        }
    },
    CHAMPAGNE_APP {
        @Override
        public boolean isApplicable(OrderSummary summary) {
            return summary.isChampagneApplicable();
        }

        @Override
        public String getMessage(OrderSummary summary) {
            return "증정 이벤트: -" + MoneyFormatter.format(Menu.CHAMPAGNE.getPrice());
        }
    };

    public abstract boolean isApplicable(OrderSummary summary);

    public abstract String getMessage(OrderSummary summary);
}
