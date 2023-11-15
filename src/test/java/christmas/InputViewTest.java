package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.InputProvider;
import christmas.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("방문일과 주문 내역 입력값 테스트")
public class InputViewTest {
    private static final String INVALID_DATE = "[ERROR] 유효하지 않은 날짜";
    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문";
    private static final String OVER_MAX_ORDERS = "[ERROR] 메뉴는 한 번에 최대 20개까지만 ";
    private static InputProvider inputProvider;

    void setInput(String input) {
        inputProvider = () -> input;
    }

    @Test
    @DisplayName("숫자가 아닌 영문 입력 시 에러 발생")
    void 방문일에_영문_입력_시_에러_발생() {
        String input = "brbrbr";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    @DisplayName("숫자가 아닌 한글 입력 시 에러 발생")
    void 방문일에_한글_입력_시_에러_발생() {
        String input = "준";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    @DisplayName("숫자가 아닌 특수문자 입력 시 에러 발생")
    void 방문일에_특수문자_입력_시_에러_발생() {
        String input = "#";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    @DisplayName("올바르지 않은 숫자 입력 시 에러 발생1")
    void 방문일에_올바르지_않은_숫자_입력_시_에러_발생_1() {
        String input = "0";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    @DisplayName("올바르지 않은 숫자 입력 시 에러 발생2")
    void 방문일에_올바르지_않은_숫자_입력_시_에러_발생_2() {
        String input = "32";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    @DisplayName("올바르지 않은 숫자 입력 시 에러 발생3")
    void 주문일에_올바르지_않은_숫자_입력_시_에러_발생_3() {
        String input = "-1000";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    @DisplayName("중복 주문 시 에러 발생")
    void 중복_주문_시_에러_발생() {
        String input = "코카콜라-2,타파스-3,코카콜라-5";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readMenu)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 주문 시 에러 발생")
    void 존재하지_않는_메뉴_주문_시_에러_발생() {
        String input = "탕수육-5";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readMenu)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @Test
    @DisplayName("어떤 메뉴를 1개미만 주문 시 에러 발생")
    void 어떤_메뉴를_1개_미만_주문_시_에러_발생() {
        String input = "바비큐립-0,코카콜라-5";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readMenu)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @Test
    @DisplayName("한 주문 당 20개를 초과하는 메뉴 주문시 에러 발생")
    void 한_주문_당_20개_초과_주문_시_에러_발생() {
        String input = "바비큐립-20,코카콜라-5";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readMenu)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OVER_MAX_ORDERS);
    }
}
