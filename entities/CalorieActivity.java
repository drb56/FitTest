


import java.util.Calendar;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class CalorieActivity extends Calorie
{
    private String activity;
    private Calendar calendar;

    public CalorieActivity()
    {
        super(0);
    }

    public CalorieActivity(int cal, String act)
    {
        super(cal);
        activity = act;
    }

    public void setActivity(String act)
    {
        activity = act;
    }

    public void setCalendar(Calendar calendar)
    {
        this.calendar = calendar;
    }

    public Calendar getCalendar()
    {
        return calendar;
    }

    public String getActivity()
    {
        return activity;
    }
}
