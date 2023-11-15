package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.common.DiscountConstants;
import christmas.dto.OrderDTO;
import christmas.dto.OrderSummary;
import christmas.model.DiscountInfo;
import christmas.model.OrderInfo;
import christmas.service.OrderService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    void testIsOrderAboveTenThousand() {
        OrderService service = new OrderService();

        // 주문 항목 생성
        List<OrderInfo> orderItems = Arrays.asList(
                new OrderInfo("티본스테이크", 1),
                new OrderInfo("바비큐립", 1),
                new OrderInfo("초코케이크", 2),
                new OrderInfo("제로콜라", 1)
        );

        // 금액은 만원 미만으로 설정
        int totalPrice = 8000;

        OrderDTO orderDTO = new OrderDTO(orderItems, totalPrice);

        boolean[] discountTypes = {false, false}; // 주말 메인 할인, 평일 디저트 할인 여부
        int[] discounts = {1000, 0}; // 일일 할인, 별 할인
        DiscountInfo discountInfo = new DiscountInfo(discounts, discountTypes);

        OrderSummary summary = service.calculateOrderSummary(orderDTO, discountInfo);

        assertThat(summary.isDiscountApplicable()).isFalse();
    }

    @Test
    void testCalculateWeekdayDessertDiscount() {
        OrderService service = new OrderService();

        // 주문 항목 생성
        List<OrderInfo> orderItems = Arrays.asList(
                new OrderInfo("초코케이크", 3),
                new OrderInfo("아이스크림", 1),
                new OrderInfo("제로콜라", 1)
        );

        // DiscountInfo 생성 (평일 디저트 할인 적용)
        DiscountInfo discountInfo = new DiscountInfo(new int[]{0, 0}, new boolean[]{false, true});

        OrderSummary orderSummary = service.calculateOrderSummary(new OrderDTO(orderItems, 20000), discountInfo);

        assertThat(orderSummary.getWeekdayDessertDiscount()).isEqualTo(4 * DiscountConstants.DESSERT_DISCOUNT_WEEKDAY);
    }


    @Test
    void testCalculateWeekendMainDiscount() {
        OrderService service = new OrderService();
        List<OrderInfo> orderItems = Arrays.asList(
                new OrderInfo("초코케이크", 3),
                new OrderInfo("아이스크림", 1),
                new OrderInfo("제로콜라", 1),
                new OrderInfo("티본스테이크", 3)
        );
        // DiscountInfo 생성 (평일 디저트 할인 적용)
        DiscountInfo discountInfo = new DiscountInfo(new int[]{0, 0}, new boolean[]{true, false});

        OrderSummary orderSummary = service.calculateOrderSummary(new OrderDTO(orderItems, 20000), discountInfo);

        assertThat(orderSummary.getWeekendMainDiscount()).isEqualTo(3 * DiscountConstants.MAIN_DISCOUNT_WEEKEND);
    }

    @Test
    void testTotalDiscountCalculation() {
        OrderService service = new OrderService();
        List<OrderInfo> orderItems = Arrays.asList(
                new OrderInfo("초코케이크", 3),
                new OrderInfo("아이스크림", 1),
                new OrderInfo("제로콜라", 1),
                new OrderInfo("티본스테이크", 3)
        );
        // DiscountInfo 생성 (평일 디저트 할인 적용)
        DiscountInfo discountInfo = new DiscountInfo(new int[]{2000, 1000}, new boolean[]{false, true});

        OrderSummary orderSummary = service.calculateOrderSummary(new OrderDTO(orderItems, 20000), discountInfo);

        assertThat(orderSummary.getTotalDiscount()).isEqualTo(
                4 * DiscountConstants.MAIN_DISCOUNT_WEEKEND + 2000 + 1000);
    }

    @Test
    void testFinalPriceCalculation() {
        OrderService service = new OrderService();
        List<OrderInfo> orderItems = Arrays.asList(
                new OrderInfo("초코케이크", 3),
                new OrderInfo("아이스크림", 1),
                new OrderInfo("티본스테이크", 4)
        );
        // DiscountInfo 생성 (평일 디저트 할인 적용)
        DiscountInfo discountInfo = new DiscountInfo(new int[]{2000, 1000}, new boolean[]{false, true});

        OrderSummary orderSummary = service.calculateOrderSummary(new OrderDTO(orderItems, 20000), discountInfo);

        assertThat(orderSummary.getFinalPrice()).isEqualTo(
                20000 - 4 * DiscountConstants.MAIN_DISCOUNT_WEEKEND - 2000 - 1000);
    }

    @Test
    void testEventBadgeDetermination() {
        OrderService service = new OrderService();
        int totalDiscount = 5000; // 예시 할인 금액

        String badge = service.determineEventBadge(totalDiscount);

        // 가정: 특정 할인 금액에 따른 뱃지 산출 로직에 따라
        String expectedBadge = "별"; // 예상되는 뱃지
        assertThat(badge).isEqualTo(expectedBadge);
    }
}

