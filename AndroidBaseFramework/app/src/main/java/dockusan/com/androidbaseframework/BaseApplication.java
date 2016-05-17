package dockusan.com.androidbaseframework;

import android.app.Application;


import dockusan.com.androidbaseframework.callbacks.ActivityLifecycleCallback;
import dockusan.com.androidbaseframework.utils.SharedPrefUtils;

/**
 * Created by SF on 11/05/2016.
 */
public class BaseApplication extends Application{
    private static BaseApplication instance;
    private static SharedPrefUtils sharedPreferences;
    private static boolean mIsAppRunning;

    public BaseApplication() {
        instance = this;
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallback(new ActivityLifecycleCallback.OnAppRunningListener() {
            @Override
            public void isAppRunning(boolean isRunning) {
                mIsAppRunning = isRunning;
            }
        }));
        sharedPreferences = new SharedPrefUtils(getApplicationContext());

    }



    public static SharedPrefUtils getSharedPreferences() {
        return sharedPreferences;
    }
    public static boolean isAppRunning(){
        return mIsAppRunning;
    }
}
