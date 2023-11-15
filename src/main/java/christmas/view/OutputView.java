package christmas.view;

import christmas.common.enums.DiscountType;
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
            System.out.println(
                    String.join(" ", orderItem.getName(), orderItem.getQuantity() + "개"));
        }
    }

    public void displayOrderSummary(DateDTO dateDTO, OrderDTO orderDTO, OrderSummary orderSummary) {
        displayOrderedItems(dateDTO, orderDTO);
        System.out.println("\n<할인 전 총주문 금액>\n" + MoneyFormatter.format(orderDTO.getTotalPrice()));

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
            return;
        }
        System.out.println("없음");
    }

    private void displayDiscountDetails(OrderSummary orderSummary) {
        System.out.println("\n<혜택 내역>");
        boolean hasDiscounts = false;

        for (DiscountType discountType : DiscountType.values()) {
            if (discountType.isApplicable(orderSummary)) {
                System.out.println(discountType.getMessage(orderSummary));
                hasDiscounts = true;
            }
        }

        if (!hasDiscounts) {
            System.out.println("없음");
        }
    }

    private void displayTotalDiscount(OrderSummary orderSummary) {
        String discountDisplay = getFormattedDiscount(orderSummary.getTotalDiscount());
        System.out.println("\n<총혜택 금액>\n" + discountDisplay);
    }

    private String getFormattedDiscount(int totalDiscount) {
        if (totalDiscount == 0) {
            return "0원";
        }
        return "-" + MoneyFormatter.format(totalDiscount);
    }


    private void displayFinalAmount(OrderSummary orderSummary) {
        System.out.println("\n<할인 후 예상 결제 금액>\n" + MoneyFormatter.format(orderSummary.getFinalAmount()));
    }

    private void displayEventBadge(OrderSummary orderSummary) {
        System.out.print("\n<12월 이벤트 배지>\n" + orderSummary.getEventBadge());
    }
}
