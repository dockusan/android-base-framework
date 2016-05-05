package dockusan.com.androidbaseframework.callbacks;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * ActivityLifecycleCallbacks.
 *
 * @author DaoLQ
 */
public class ActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks {
    /**
     * define Interface
     */
    public interface OnAppRunningListener {
        void isAppRunning(boolean isRunning);
    }

    public ActivityLifecycleCallback(OnAppRunningListener listener) {
        this.mOnAppRunningListener = listener;
    }

    private final OnAppRunningListener mOnAppRunningListener;
    private int numRunningActivity;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (++numRunningActivity >= 1) {
            mOnAppRunningListener.isAppRunning(true);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (--numRunningActivity == 0) {
            // Stop or cancel something in your app, when app in background.
            mOnAppRunningListener.isAppRunning(false);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
