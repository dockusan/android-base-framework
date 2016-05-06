package dockusan.com.androidbaseframework.utils;

/**
 * Created by SF on 05/05/2016.
 */
public class StringUtil {

    public static boolean checkStringNull(String data) {
        if (data != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkStringValid(String data) {
        if (data != null && !data.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkZero(String data) {
        if (checkStringValid(data) && !data.equals("0"))
            return true;
        else
            return false;
    }
}
