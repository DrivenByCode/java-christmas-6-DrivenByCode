package christmas.service;

import christmas.dto.OrderDTO;
import christmas.dto.OrderSummary;
import christmas.model.DiscountInfo;

public class OrderService {

    public OrderSummary calculateOrderSummary(OrderDTO orderDTO, DiscountInfo discountInfo) {
        int totalOrderAmount = orderDTO.getTotalPrice();
        boolean isEligibleForChampagne = totalOrderAmount >= 120_000;
        int totalDiscount = calculateTotalDiscount(discountInfo, isEligibleForChampagne);
        int finalAmount = totalOrderAmount - totalDiscount;
        if (discountInfo.isChampagneApplicable()) {
            finalAmount += 25_000;
        }
        String eventBadge = determineEventBadge(totalDiscount);

        return new OrderSummary(totalOrderAmount, discountInfo, totalDiscount, finalAmount, eventBadge,
                isEligibleForChampagne);
    }

    private int calculateTotalDiscount(DiscountInfo discountInfo, boolean isEligibleForChampagne) {
        int totalDiscount = discountInfo.getDailyDiscount() +
                discountInfo.getWeekdayDessertDiscount() +
                discountInfo.getWeekendMainDiscount() +
                discountInfo.getSpecialDiscount();
        if (isEligibleForChampagne) {
            totalDiscount += 25000; // 샴페인 증정 이벤트
        }
        return totalDiscount;
    }

    private String determineEventBadge(int totalDiscount) {
        if (totalDiscount >= 20_000) {
            return "산타";
        }
        if (totalDiscount >= 10_000) {
            return "트리";
        }
        if (totalDiscount >= 5_000) {
            return "별";
        }
        return "없음";
    }
}



