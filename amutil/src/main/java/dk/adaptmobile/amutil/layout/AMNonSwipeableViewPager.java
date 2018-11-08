package dk.adaptmobile.amutil.layout;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by thushanthsivakumar on 05/09/14.
 */
public class AMNonSwipeableViewPager extends ViewPager {

    public AMNonSwipeableViewPager(Context context) {
        super(context);
    }

    public AMNonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }
}
