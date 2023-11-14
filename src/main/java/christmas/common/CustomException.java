package christmas.common;


import christmas.common.enums.ErrorMessages;

public class CustomException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR]";

    public CustomException() {

        super(String.join(" ", ERROR_PREFIX, ErrorMessages.INVALID_ORDER.getMessage()));
    }

    public CustomException(String message) {

        super(String.join(" ", ERROR_PREFIX, message));
    }
}
