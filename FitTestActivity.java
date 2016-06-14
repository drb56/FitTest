package grapecity.fittest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import com.google.android.gms.fit.samples.common.logger.Log;
//import com.google.android.gms.fit.samples.common.logger.LogView;
//import com.google.android.gms.fit.samples.common.logger.LogWrapper;
//import com.google.android.gms.fit.samples.common.logger.MessageOnlyLogFilter;


public class FitTestActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        // This method sets up our custom logger, which will print all log messages to the device
        // screen, as well as to adb logcat.
//        initializeLogging();
//
//        // When permissions are revoked the app is restarted so onCreate is sufficient to check for
//        // permissions core to the Activity's functionality.
//        if (!checkPermissions())
//        {
//            requestPermissions();
//        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FitTestFragment articleFragment = new FitTestFragment();
        transaction.replace(R.id.fragment_container, articleFragment);
        transaction.commit();
    }



//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//
//        // This ensures that if the user denies the permissions then uses Settings to re-enable
//        // them, the app will start working.
//        buildFitnessClient();
//    }

//    private void buildFitnessClient()
//    {
//        if (mClient == null && checkPermissions())
//        {
//            mClient = new GoogleApiClient.Builder(this)
//                    .addApi(Fitness.SENSORS_API)
//                    .addScope(new Scope(Scopes.FITNESS_LOCATION_READ))
//                    .addConnectionCallbacks(
//                            new GoogleApiClient.ConnectionCallbacks()
//                            {
//                                @Override
//                                public void onConnected(Bundle bundle)
//                                {
//                                    Log.i(TAG, "Connected!!!");
//                                    // Now you can make calls to the Fitness APIs.
//                                    findFitnessDataSources();
//                                }
//
//                                @Override
//                                public void onConnectionSuspended(int i)
//                                {
//                                    // If your connection to the sensor gets lost at some point,
//                                    // you'll be able to determine the reason and react to it here.
//                                    if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST)
//                                    {
//                                        Log.i(TAG, "Connection lost.  Cause: Network Lost.");
//                                    } else if (i
//                                            == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED)
//                                    {
//                                        Log.i(TAG,
//                                                "Connection lost.  Reason: Service Disconnected");
//                                    }
//                                }
//                            }
//                    )
//                    .enableAutoManage(this, 0, new GoogleApiClient.OnConnectionFailedListener()
//                    {
//                        @Override
//                        public void onConnectionFailed(ConnectionResult result)
//                        {
//                            Log.i(TAG, "Google Play services connection failed. Cause: " +
//                                    result.toString());
//                            Snackbar.make(
//                                    FitTestActivity.this.findViewById(R.id.main_activity_view),
//                                    "Exception while connecting to Google Play services: " +
//                                            result.getErrorMessage(),
//                                    Snackbar.LENGTH_INDEFINITE).show();
//                        }
//                    })
//                    .build();
//        }
//    }
}
