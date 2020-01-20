package tingtel.android.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import tingtel.android.models.ApplicationModel;

import static tingtel.android.utils.AppUtils.getSessionManagerInstance;

public class MyApplication extends Application implements LifecycleObserver {

    private static MyApplication myApplication;
    private static final String CUSTOMER_SESSION = "Tingtelpref";



    public static MyApplication getInstance() {
        return myApplication;
    }

    public static SharedPreferences getSharedPreferencesCustomer() {
        return myApplication.getSharedPreferences(CUSTOMER_SESSION, Context.MODE_PRIVATE);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }


    // Check if app is in foreground (lifecycle library)
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void appInResumeState() {
        // Toast.makeText(this,"In Foreground",Toast.LENGTH_LONG).show();
        Log.e("logmessage", "In Foreground");

        //   checkSimCards();
        ApplicationModel applicationModel = new ApplicationModel();

        applicationModel.setAppstate("foreground");

        SessionManager sessionManager = getSessionManagerInstance();
        sessionManager.setAppstate("foreground");
    }


    //Check if app is in background
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void appInPauseState() {
        Log.e("logmessage", "In Background");
        SessionManager sessionManager = getSessionManagerInstance();
        sessionManager.setAppstate("background");
       // applicationModel.setAppstate("background");
    }
}
