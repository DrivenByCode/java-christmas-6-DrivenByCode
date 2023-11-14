package christmas.dto;

public class DateDTO {
    private int date;
    private int DayOfTheWeek;

    public DateDTO(final int date, final int dayOfTheWeek) {
        this.date = date;
        DayOfTheWeek = dayOfTheWeek;
    }

    public int getDate() {
        return date;
    }

    public void setDate(final int date) {
        this.date = date;
    }

    public int getDayOfTheWeek() {
        return DayOfTheWeek;
    }

    public void setDayOfTheWeek(final int dayOfTheWeek) {
        DayOfTheWeek = dayOfTheWeek;
    }
}
