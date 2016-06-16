package grapecity.fittest;

//import android.app.Fragment;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.ConfigApi;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.fitness.result.DataTypeResult;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.location.DetectedActivity;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import grapecity.fittest.entities.ActivityDataPoint;
import grapecity.fittest.entities.CalorieActivity;
import grapecity.fittest.entities.DayActivities;

/**
 * Created by David.Bickford on 6/9/2016.
 */
public class FitTestFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks
{
    private GoogleApiClient mClient = null;
    private static ArrayList<DayActivities> activities;
    private static ArrayList<DayActivities> calories;
    private static ArrayList<ActivityDataPoint> activityDataPointList;
    private static boolean added;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activities_view, container, false);

        activities = new ArrayList<>();
        activityDataPointList = new ArrayList<>();
        added = false;

        mClient = new GoogleApiClient.Builder(getContext())
                .addApi(Fitness.HISTORY_API)
                .addApi(Fitness.SESSIONS_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addConnectionCallbacks(this)
                .enableAutoManage(getActivity(), 0, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.i("MyApp", "Google Play services connection failed. Cause: " +
                                result.toString());
                }})
                .build();

        return v;
    }


    private static void dumpDataSet(DataSet dataSet)
    {
        ArrayList<String> activityList = new ArrayList<>();
        for(int i=0; i<=112; i++)
        {
            activityList.add(i, "");
        }
        activityList.add(0, "Driving");
        activityList.add(2, "On Foot");
        activityList.add(3, "Still(Not Moving)");
        activityList.add(7, "Walking");
        activityList.add(8, "Running");
        activityList.add(97, "Weight Lifting");
//        CalorieActivity currActivity;
//        DayActivities currDay;
        Calendar currDate = Calendar.getInstance();

//        Log.i("MyApp", "Data returned for Data type: " + dataSet.getDataType().getName());
        java.text.DateFormat dateFormat = SimpleDateFormat.getTimeInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        for (DataPoint dp : dataSet.getDataPoints())
        {
            date = new Date(dp.getTimestamp(TimeUnit.MILLISECONDS));
//            Log.i("MyApp", "Data point:");
//            Log.i("MyApp", "\tType: " + dp.getDataType().getName());
//            Log.i("MyApp", "\tDate: " + df2.format(date));
//            Log.i("MyApp", "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
//            Log.i("MyApp", "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));
//            for(Field field : dp.getDataType().getFields())
//            {
//                Log.i("MyApp", "\tField: " + field.getName() + " Value: " + dp.getValue(field));
//            }
            int calories;
            String activity;

            ActivityDataPoint currActivity = new ActivityDataPoint();
            currDate.setTime(new Date(dp.getStartTime(TimeUnit.MILLISECONDS)));
            currActivity.setStartDate(currDate);
            currDate.setTime(new Date(dp.getEndTime(TimeUnit.MILLISECONDS)));
            currActivity.setEndDate(currDate);
            for(Field field : dp.getDataType().getFields())
            {
                if(field.getName().equals("activity"))
                {
                    currActivity.setActivity(activityList.get(Integer.parseInt(dp.getValue(field).toString())));
                }
                else if(field.getName().equals("calories"))
                {
                    currActivity.setCalories((int)Double.parseDouble(dp.getValue(field).toString()));
                }
            }
            activityDataPointList.add(currActivity);


//            currDay = new DayActivities();
//            currDate.setTime(date);
//            currDay.setCalendar(currDate);
//            int found = 0;
//
//            if(!added)
//            {
//                activities.add(currDay);
//                added = true;
//            }
//
//            for(int i=0; i<activities.size(); i++)
//            {
//                DayActivities checkDay = activities.get(i);
//                if(checkDay.getDate().equals(currDay.getDate()))
//                {
//                    currDay = activities.get(i);
//                    for(Field field : dp.getDataType().getFields())
//                    {
//                        currActivity = new CalorieActivity();
//                        if(field.getName().equals("activity"))
//                        {
//                            currActivity.setActivity(activityList.get(Integer.parseInt(dp.getValue(field).toString())));
//                            date = new Date(dp.getTimestamp(TimeUnit.MILLISECONDS));
//                            currDate.setTime(date);
//                            currActivity.setCalendar(currDate);
//                            currDay.addActivity(currActivity);
//                        }
//                        else if(field.getName().equals("calories"))
//                        {
//                            currActivity.setCalorie((int)Double.parseDouble(dp.getValue(field).toString()));
//                            date = new Date(dp.getTimestamp(TimeUnit.MILLISECONDS));
//                            currDate.setTime(date);
//                            currActivity.setCalendar(currDate);
//                            currDay.addActivity(currActivity);
//                        }
//                    }
//                    found = 1;
//                }
//                else if(found == 0 && i == activities.size()-1)
//                {
//                    activities.add(currDay);
//                }
//            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        new InsertAndVerifyDataTask().execute();
    }

    private class InsertAndVerifyDataTask extends AsyncTask<Void, Void, Void>
    {
        protected Void doInBackground(Void... params)
        {

            Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            long endTime = cal.getTimeInMillis();
            cal.add(Calendar.WEEK_OF_YEAR, -1);
            long startTime = cal.getTimeInMillis();

            DataReadRequest readRequest = new DataReadRequest.Builder()
                    .aggregate(DataType.TYPE_CALORIES_EXPENDED, DataType.AGGREGATE_CALORIES_EXPENDED)
                    .aggregate(DataType.TYPE_ACTIVITY_SEGMENT, DataType.AGGREGATE_ACTIVITY_SUMMARY)
                    .bucketByTime(1, TimeUnit.DAYS)
                    .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                    .build();

            DataReadResult dataReadResult = Fitness.HistoryApi.readData(mClient, readRequest).await(1, TimeUnit.MINUTES);

            if (dataReadResult.getBuckets().size() > 0)
            {
                Log.i("MyApp", "Number of returned buckets of DataSets is: "
                        + dataReadResult.getBuckets().size());
                for (Bucket bucket : dataReadResult.getBuckets())
                {
                    List<DataSet> dataSets = bucket.getDataSets();
//                    Log.i("MyApp", "dataSets size " + dataSets.size());
                    for (DataSet dataSet : dataSets)
                    {
                        dumpDataSet(dataSet);
                    }
                }
            }
            else
            {
                Log.i("MyApp", "No data");
            }

            SimpleDateFormat df2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            SimpleDateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
            for(int i=0; i<activityDataPointList.size(); i++)
            {
                ActivityDataPoint currDay = activityDataPointList.get(i);
                Log.i("MyApp", "Activity for: " + df1.format(currDay.getStartDate().getTime()));
                Log.i("MyApp", "\tActivity start: " + df2.format(currDay.getStartDate().getTime()));
                Log.i("MyApp", "\tActivity end: " + df2.format(currDay.getEndDate().getTime()));
                Log.i("MyApp", "\tActivity : " + currDay.getActivity());
                Log.i("MyApp", "\tCalories : " + currDay.getCalories());
            }

//            SimpleDateFormat df2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//            for(int i=0; i<activities.size(); i++)
//            {
//                DayActivities currDay = activities.get(i);
//                Log.i("MyApp", "Activities for: " + df2.format(currDay.getCalendar().getTime()));
//                for(int j=0; j<currDay.getActivities().size(); j++)
//                {
//                    CalorieActivity currActivity = (CalorieActivity)currDay.getActivities().get(j);
//                    Log.i("MyApp", "\tActivity for: " + df2.format(currActivity.getCalendar().getTime()));
//                    Log.i("MyApp", "\tActivity : " + currActivity.getActivity());
//                    Log.i("MyApp", "\tCalories : " + currActivity.getCalorie());
//                }
//            }





//            SessionReadRequest sessionReadRequest = new SessionReadRequest.Builder()
//                    .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS)
//                    .setSessionName("Week")
//                    .read(DataType.TYPE_CALORIES_EXPENDED)
//                    .build();
//
//
//            SessionReadResult sessionReadResult = Fitness.SessionsApi.readSession(mClient, sessionReadRequest).await(1, TimeUnit.MINUTES);
//
//            Log.i("MyApp", "Number of returned sessions is: "
//                    + sessionReadResult.getSessions().size());
//
//            if (sessionReadResult.getSessions().size() > 0)
//            {
//                Log.i("MyApp", "Number of returned sessions is: "
//                        + sessionReadResult.getSessions().size());
//                for (Session session : sessionReadResult.getSessions())
//                {
//                    List<DataSet> dataSets = sessionReadResult.getDataSet(session);
//                    for (DataSet dataSet : dataSets)
//                    {
//                        dumpDataSet(dataSet);
//                    }
//                }
//            }
//            else
//            {
//                Log.i("MyApp", "No data");
//            }

            return null;
        }
    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }
}
