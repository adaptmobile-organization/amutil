package dk.adaptmobile.amutil.layout;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by christian on 11/08/15.
 */
public class AMSlidingMenuWrapper extends RelativeLayout {


    private final Context context;
    private boolean slideEnabled;
    private AMSlidingMenu menu;
    private float downX;
    private float movePixels;
    private int slidePercentage;

    public AMSlidingMenuWrapper(Context context) {
        this(context, null);
    }

    public AMSlidingMenuWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!slideEnabled) {
            return false;
        }

        final int action = ev.getAction() & MotionEventCompat.ACTION_MASK;


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                return false;
            case MotionEvent.ACTION_MOVE:
                int swipeMargin = (AMLayoutUtil.getScreenWidth(context) / 100) * slidePercentage;
                if (downX > 0 && downX < swipeMargin && (ev.getX() - downX) > 20){ //If the event is within left 2% of the screen
                    return true;
                }else{
                    return false;
                }

        }


        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!slideEnabled) {
            return false;
        }


        final int action = ev.getAction() & MotionEventCompat.ACTION_MASK;


        switch (action) {
            case MotionEvent.ACTION_MOVE:
                movePixels = ev.getX() - downX;
                menu.handleDrag(movePixels);
                return true;
            case MotionEvent.ACTION_UP:
                downX = 0;
                menu.cancelDrag(movePixels);
                return false;
            case MotionEvent.ACTION_CANCEL:
                downX = 0;
                menu.cancelDrag(movePixels);
                return false;
        }


        return false;
    }


    public void setSlideEnabled(boolean slideEnabled) {
        this.slideEnabled = slideEnabled;
    }

    public void setMenu(AMSlidingMenu menu) {
        this.menu = menu;
    }

    public void setSlidePercentage(int slidePercentage) {
        this.slidePercentage = slidePercentage;
    }
}
