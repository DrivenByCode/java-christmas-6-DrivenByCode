package christmas.model;

import christmas.dto.DateDTO;

public class DateInfo {
    public static DateDTO createDateDTO(String input) {
        int date = Integer.parseInt(input);
        int dayOfTheWeek = (date + 4) % 7;
        return new DateDTO(date, dayOfTheWeek);
    }
}
