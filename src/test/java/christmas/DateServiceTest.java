package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.common.DiscountConstants;
import christmas.dto.DateDTO;
import christmas.model.DateModel;
import christmas.model.DiscountInfo;
import christmas.service.DateService;
import org.junit.jupiter.api.Test;

class DateServiceTest {

    @Test
    void testStarDayDiscount() {
        DateService service = new DateService();
        DateDTO dateDTO = DateModel.createDateDTO("25");
        DiscountInfo discount = service.calculateDiscounts(dateDTO);

        assertThat(discount.getStarDiscount()).isEqualTo(DiscountConstants.SPECIAL_DISCOUNT);
    }

    @Test
    void testWeekdayDiscount() {
        DateService service = new DateService();
        DateDTO dateDTO = DateModel.createDateDTO("14");
        DiscountInfo discount = service.calculateDiscounts(dateDTO);

        assertThat(discount.isWeekdayDessertDiscount()).isTrue();
    }

    @Test
    void testWeekendDiscount() {
        DateService service = new DateService();
        DateDTO dateDTO = DateModel.createDateDTO("15");
        DiscountInfo discount = service.calculateDiscounts(dateDTO);

        assertThat(discount.isWeekendMainDiscount()).isTrue();
    }

    @Test
    void testStarDiscountAmount() {
        DateService service = new DateService();
        DateDTO dateDTO = DateModel.createDateDTO("20");
        DiscountInfo discount = service.calculateDiscounts(dateDTO);

        assertThat(discount.getStarDiscount()).isEqualTo(0);
    }

    @Test
    void testChristmasDayDiscountAmount() {
        DateService service = new DateService();
        DateDTO dateDTO = DateModel.createDateDTO("25");
        DiscountInfo discount = service.calculateDiscounts(dateDTO);

        assertThat(discount.getDailyDiscount()).isGreaterThan(0);
    }
}
