package dk.adaptmobile.amutil.layout;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Helper methods for working with layouts
 */
public class AMLayoutUtil {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Returns the width of the screen in pixels
     * @param context A context
     * @return Screen with in pixels
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * Returns the height of the screen in pixels
     * @param context A context
     * @return Screen height in pixels
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dpFromPx(float px, Context context) {
        float screenDensity = context.getResources().getDisplayMetrics().density;
        return (int) (px / screenDensity);
    }

    public static int pxFromDp(float dp, Context context) {
        float screenDensity = context.getResources().getDisplayMetrics().density;
        return (int) (dp * screenDensity);
    }


    public static void onGlobalLayout(final View view, final OnCompleteCallback onCompleteCallback) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onCompleteCallback.onComplete();
                if (Build.VERSION.SDK_INT < 16) {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    //TODO - Do we need this? The statusbar should always be the same height?
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * FROM: http://stackoverflow.com/questions/1714297/android-view-setidint-id-programmatically-how-to-avoid-id-conflicts
     * Generate a value suitable for use in {@link #//setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    //TODO - Do we need this? When would we need to add a resource id at runtime?
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
