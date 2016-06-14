package grapecity.fittest.entities;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class DayActivities
{
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private ArrayList<CalorieActivity> activities;
    private String date;

    public DayActivities()
    {
        activities = new ArrayList<CalorieActivity>();
    }

    public DayActivities(int y, int m, int d, ArrayList act)
    {
        calendar = Calendar.getInstance();
        year = y;
        month = m;
        day = d;
        calendar.set(year, month, day);
        activities = new ArrayList<CalorieActivity>();
        for(int i=0; i<act.size(); i++)
        {
            activities.add((CalorieActivity)act.get(i));
        }
        date = DATE_FORMAT.format(calendar.getTime());
    }

    public String getDate()
    {
        return date;
    }

    public int getSize()
    {
        return activities.size();
    }

    public void setCalendar(Calendar cal)
    {
//        year = y;
//        month = m;
//        day = d;
        calendar = cal;
//        calendar.set(year, month, day);
        date = DATE_FORMAT.format(calendar.getTime());
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

    public void addActivity(CalorieActivity act)
    {
        activities.add(act);
    }

    public ArrayList getActivities()
    {
        return activities;
    }

    public CalorieActivity getSpecificActivity(int index)
    {
        return activities.get(index);
    }

    public void setSpecificActivity(int index, String act)
    {
        CalorieActivity currAct = activities.get(index);
        currAct.setActivity(act);
    }
}
