package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.InputProvider;
import christmas.view.InputView;
import org.junit.jupiter.api.Test;

public class InputViewTest {
    private static final String INVALID_DATE = "[ERROR] 유효하지 않은 날짜";
    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문";
    private static final String OVER_MAX_ORDERS = "[ERROR] 메뉴는 한 번에 최대 20개까지만 ";
    private static InputProvider inputProvider;

    void setInput(String input) {
        inputProvider = () -> input;
    }

    @Test
    void testInputtingEnglishOnDate() {
        String input = "brbrbr";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    void testInputtingKoreanOnDate() {
        String input = "준";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    void testInputtingSpecialCharacterOnDate() {
        String input = "#";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    void testInvalidNumberOnDate_1() {
        String input = "0";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    void testInvalidNumberOnDate_2() {
        String input = "32";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    void testInvalidNumberOnDate_3() {
        String input = "-1000";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE);
    }

    @Test
    void testDuplicatedOrder() {
        String input = "코카콜라-2,타파스-3,코카콜라-5";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readMenu)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @Test
    void testNotExistMenuOnOrder() {
        String input = "탕수육-5";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readMenu)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @Test
    void testInvalidQuantityOnOrder() {
        String input = "바비큐립-0,코카콜라-5";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readMenu)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER);
    }

    @Test
    void testOverQuantityOnOrder() {
        String input = "바비큐립-20,코카콜라-5";
        setInput(input);
        InputView inputView = new InputView(inputProvider);
        assertThatThrownBy(inputView::readMenu)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OVER_MAX_ORDERS);
    }
}
