package christmas.common.enums;

public enum ServiceMessages {
    FIRST_MSG("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    VISTED_DAY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    BEFORE_RESULT("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    MENU_NOTICE("<주문 메뉴>"),
    NOTICE_AMOUNT("총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.");
    private final String message;
    ServiceMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
