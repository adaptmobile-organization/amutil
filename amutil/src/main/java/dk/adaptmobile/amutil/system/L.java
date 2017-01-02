package dk.adaptmobile.amutil.system;

import android.util.Log;

import dk.adaptmobile.amutil.date.AMDateUtil;

/**
 * Prints messages from the application to logcat
 */
public class L {

    /**
     * Logs a string to the console
     * @param message The string to be logged
     */
    public static void og(String message) {
        String callerClassName = new Exception().getStackTrace()[1].getClassName();
        Log.d(callerClassName, message);
    }

    /**
     * Logs a int to the console
     * @param number The integer to be logged
     */
    public static void og(int number) {
        String callerClassName = new Exception().getStackTrace()[1].getClassName();
        Log.d(callerClassName, "" + number);
    }

    /**
     * Logs a string to the console with a tag
     * @param tag Tag to be used with log
     * @param message The string to be logged
     */
    public static void og(String tag, String message) {
        Log.d(tag, message);
    }

    /**
     * Logs a integer to the console with a tag
     * @param tag Tag to be used with log
     * @param number The integer to be logged
     */
    public static void og(String tag, int number) {
        Log.d(tag, "" + number);
    }
}
