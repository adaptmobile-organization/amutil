package dk.adaptmobile.amutil.misc;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Helper functions for the soft keyboard
 */

public class AMKeyboardUtil {

    /**
     * Hides the softkeyboard
     * @param activity The current activity
     * @param view The current view
     */
    public static void hideKeyboard(final Activity activity, final View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentView = activity.getCurrentFocus();
        if (currentView != null) {
            inputMethodManager.hideSoftInputFromWindow(currentView.getWindowToken(), 0);
        } else if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Shows the softkeyboard
     * @param activity The current activity
     */
    public static void showSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
