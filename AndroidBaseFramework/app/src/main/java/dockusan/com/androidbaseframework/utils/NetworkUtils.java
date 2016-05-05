package dockusan.com.androidbaseframework.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtils {

    public static final String DONT_CANCEL = "DONT_CANCEL";

    public static final String LOAD_MORE = "LOAD_MORE";

    private static NetworkUtils sInstance;


    private static Context sContext;

    /**
     * Check internet connection
     *
     * @return true if Network is connected.
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) sContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (mConnectivityManager.getActiveNetworkInfo() != null && mConnectivityManager.getActiveNetworkInfo().isAvailable() && mConnectivityManager
                .getActiveNetworkInfo().isConnected());
    }

}
