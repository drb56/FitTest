


import java.util.Calendar;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class CalorieDate extends Calorie
{
    private Calendar calendar;
    private int year;
    private int month;
    private int day;

    public CalorieDate(int calorie, int y, int m, int d)
    {
        super(calorie);
        calendar = Calendar.getInstance();
        year = y;
        month = m;
        day = d;
        calendar.set(year, month, day);
    }

    public void setCalendar(int y, int m, int d)
    {
        year = y;
        month = m;
        day = d;
        calendar.set(year, month, day);
    }

    public void setYear(int y)
    {
        year = y;
        calendar.set(year, month, day);
    }

    public void setMonth(int y)
    {
        month = y;
        calendar.set(year, month, day);
    }

    public void setDay(int y)
    {
        day = y;
        calendar.set(year, month, day);
    }

    public Calendar getCalendar()
    {
        return calendar;
    }

    public int getYear()
    {
        return year;
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }
}
