package christmas.utils.enums;
public enum ErrorMessages {
    INVALD_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_BEVERAGE("음료만 주문 시, 주문할 수 없습니다.");
    private final String message;

    ErrorMessages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
