package dk.adaptmobile.amutil.misc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.webkit.WebView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by christian on 2/18/15.
 */
public class AMUtil {
    //TODO Do we need this?
    public static String getUniqueDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    //TODO Do we need this?
    public static String getTimestamp() {
        return "" + System.currentTimeMillis();
    }

    /**
     * Checks if the application has been launched before, and sets it if not.
     * @param context
     * @return True if application hasn't been launched before, false if it has.
     */
    public static boolean isFirstLaunch(Context context) {
        final String FIRST_LAUNCH = "AM_FIRST_LAUNCH";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean firstLaunch = prefs.getBoolean(FIRST_LAUNCH, true);
        if(!firstLaunch) {
            return false;
        } else {
            prefs.edit().putBoolean(FIRST_LAUNCH, true).commit();
            return true;
        }
    }

    /**
     * Loads a body text into a webview with a stylesheet from assets. Example
     * AMUtil.loadStyledHTML(webview, "style.css", "&lt;p&gt;Example&lt;/p&gt;");
     * @param wv The webview
     * @param stylesheetName Name of the stylesheet
     * @param htmlBody The html string to put in the &lt;body&gt;
     */
    public static void loadStyledHTML(WebView wv, String stylesheetName, String htmlBody){
        String body = "<html><head><link rel=\"stylesheet\" href=\"file:///android_asset/" + stylesheetName +  "\" type=\"text/css\"><meta name='viewport'></head><body>" + htmlBody + "</body></html>";

        wv.loadDataWithBaseURL("x-data://base", body , "text/html", "UTF-8", null);
    }
}