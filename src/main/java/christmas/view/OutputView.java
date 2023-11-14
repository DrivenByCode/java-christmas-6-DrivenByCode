package christmas.view;

import christmas.common.enums.Menu;
import christmas.dto.DateDTO;
import christmas.dto.OrderDTO;
import christmas.dto.OrderSummary;
import christmas.model.OrderInfo;
import christmas.utils.MoneyFormatter;

public class OutputView {
    private void displayOrderedItems(DateDTO dateDTO, OrderDTO orderDTO) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", dateDTO.getDate());
        System.out.println("\n<주문 메뉴>");
        for (OrderInfo orderItem : orderDTO.getOrderItems()) {
            System.out.println(orderItem.getName() + " " + MoneyFormatter.format(orderItem.getQuantity()) + "개");
        }
    }

    public void displayOrderSummary(DateDTO dateDTO, OrderDTO orderDTO, OrderSummary orderSummary) {
        displayOrderedItems(dateDTO, orderDTO);
        System.out.println("\n<할인 전 총주문 금액>\n" + MoneyFormatter.format(orderDTO.getTotalPrice()) + "원");

        displayGiftItem(orderSummary);
        displayDiscountDetails(orderSummary);
        displayTotalDiscount(orderSummary);
        displayFinalAmount(orderSummary);
        displayEventBadge(orderSummary);
    }

    private void displayGiftItem(OrderSummary orderSummary) {
        System.out.println("\n<증정 메뉴>");
        if (orderSummary.isChampagneApplicable()) {
            System.out.println("샴페인 1개");
        } else {
            System.out.println("없음");
        }
    }

    private void displayDiscountDetails(OrderSummary orderSummary) {
        System.out.println("\n<혜택 내역>");
        boolean hasDiscounts = false;
        if (orderSummary.getDailyDiscount() > 0) {
            System.out.println("크리스마스 디데이 할인: -" + MoneyFormatter.format(orderSummary.getDailyDiscount()) + "원");
            hasDiscounts = true;
        }
        if (orderSummary.getWeekdayDessertDiscount() > 0) {
            System.out.println("평일 할인: -" + MoneyFormatter.format(orderSummary.getWeekdayDessertDiscount()) + "원");
            hasDiscounts = true;
        }
        if (orderSummary.getSpecialDiscount() > 0) {
            System.out.println("특별 할인: -" + MoneyFormatter.format(orderSummary.getSpecialDiscount()) + "원");
            hasDiscounts = true;
        }
        if (orderSummary.isChampagneApplicable()) {
            System.out.printf("증정 이벤트: -%s원", MoneyFormatter.format(Menu.CHAMPAGNE.getPrice()));
            hasDiscounts = true;
        }
        if (!hasDiscounts) {
            System.out.println("없음");
        }
    }

    private void displayTotalDiscount(OrderSummary orderSummary) {
        if (orderSummary.getTotalDiscount() == 0) {
            System.out.println("\n<총혜택 금액>\n" + 0 + "원");
            return;
        }
        System.out.println("\n<총혜택 금액>\n-" + MoneyFormatter.format(orderSummary.getTotalDiscount()) + "원");
    }

    private void displayFinalAmount(OrderSummary orderSummary) {
        System.out.println("\n<할인 후 예상 결제 금액>\n" + MoneyFormatter.format(orderSummary.getFinalAmount()) + "원");
    }

    private void displayEventBadge(OrderSummary orderSummary) {
        System.out.println("\n<12월 이벤트 배지>\n" + orderSummary.getEventBadge());
    }
}
