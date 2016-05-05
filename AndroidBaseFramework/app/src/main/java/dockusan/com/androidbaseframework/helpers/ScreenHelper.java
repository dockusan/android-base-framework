package dockusan.com.androidbaseframework.helpers;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import dockusan.com.androidbaseframework.application.BaseApplication;


public class ScreenHelper {
    private static int screenWidthInPx = -1;
    private static int screenHeightInPx = -1;
    private static float screenWidthInPd = -1;
    private static float screenHeightInPd = -1;
    public static int densityDpi = -1;

    public static int getScreenWidthInPx() {
        if (screenWidthInPx < 0) {
            init();
        }
        return screenWidthInPx;
    }

    public static int getScreenHeightInPx() {
        if (screenHeightInPx < 0) {
            init();
        }
        return screenHeightInPx;
    }

    public static float getScreenWidthInPd() {
        if (screenWidthInPd < 0) {
            init();
        }
        return screenWidthInPd;
    }

    public static float getScreenHeightInPd() {
        if (screenHeightInPd < 0) {
            init();
        }
        return screenHeightInPd;
    }

    public static int getDensityDpi() {
        if (densityDpi < 0) {
            init();
        }
        return densityDpi;
    }

    private static void init() {
        WindowManager wm = (WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screenHeightInPx = metrics.heightPixels;
        screenWidthInPx = metrics.widthPixels;
        screenWidthInPd = metrics.xdpi;
        screenHeightInPd = metrics.ydpi;
        densityDpi = (int) (metrics.density * 160f);
    }

    public static int dpToPx(int dp) {
        return (int) ((dp * BaseApplication.getInstance().getResources().getDisplayMetrics().density) + 0.5);
    }

    public static int pxToDp(int px) {
        return (int) ((px / BaseApplication.getInstance().getResources().getDisplayMetrics().density) + 0.5);
    }
}
