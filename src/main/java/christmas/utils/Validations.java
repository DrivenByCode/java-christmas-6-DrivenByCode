package christmas.utils;

import christmas.utils.enums.ErrorMessages;

public class Validations {
    public void isDateNumber(final String input) {
        if(!input.matches("[0-9]+")) {
            throw new IllegalArgumentException(ErrorMessages.INVALD_DATE.getMessage());
        }
    }
    public void isDateInRange(String input) {
        int date = Integer.parseInt(input);
        if(0 >= date || 31 < date) {
            throw new IllegalArgumentException(ErrorMessages.INVALD_DATE.getMessage());
        }
    }

    public void hasMenuValidCharacters(final String input) {
        if(!input.matches("[가-힣-0-9,]+")) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_ORDER.getMessage()+"99");
        }
    }
}
