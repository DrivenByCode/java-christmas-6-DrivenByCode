package christmas.dto;

public class DateDTO {
    private final int date;
    private final int DayOfTheWeek;

    public DateDTO(final int date, final int dayOfTheWeek) {
        this.date = date;
        DayOfTheWeek = dayOfTheWeek;
    }

    public int getDate() {
        return date;
    }

    public int getDayOfTheWeek() {
        return DayOfTheWeek;
    }

}
