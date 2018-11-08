package dk.adaptmobile.amutil.layout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import androidx.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import dk.adaptmobile.amutil.system.L;

/**
 * Created by christian on 24/07/15.
 */
public class AMSlidingMenu {

    private final Activity activity;
    private final ViewGroup activityRoot;
    private final ViewGroup menu;
    private final ViewGroup activityWrapper;
    private final RelativeLayout overlay;
    private final boolean fadeOverlayEnabled;
    private final LeftMenuType type;
    private final int screenWidth;
    private ArrayList<View> views;
    private int width;
    private boolean menuShowing;
    private AMSlidingMenuStateListener menuStateListener;
    private AMSlidingMenuWrapper activityGestureWrapper;

    public void setSlideEnabled(boolean enabled) {
        activityGestureWrapper.setSlideEnabled(enabled);
    }


    public enum LeftMenuType {
        LEFT, RIGHT
    }

    public interface AMSlidingMenuStateListener {
        void opened();
        void closed();
    }

    public void setSlidingMenuStateListener(AMSlidingMenuStateListener listener) {
        this.menuStateListener = listener;
    }

    public AMSlidingMenu(Activity activity, LeftMenuType type, @LayoutRes int menuLayoutId, int width, boolean fadeOverlayEnabled, boolean swipeEnabled, int swipePercentage) {
        this.activity = activity;
        this.width = width;
        this.fadeOverlayEnabled = fadeOverlayEnabled;
        this.type = type;
        screenWidth = AMLayoutUtil.getScreenWidth(activity);

        activityRoot = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        activityGestureWrapper = new AMSlidingMenuWrapper(activity);
        activityGestureWrapper.setLayoutParams(activityRoot.getLayoutParams());
        activityWrapper = new RelativeLayout(activity);
        activityWrapper.setLayoutParams(activityRoot.getLayoutParams());

        //Wraps every view in the activity rootview in a RelativeLayout
        views = new ArrayList<>();
        for (int i = 0; i < activityRoot.getChildCount(); i++) {
            views.add(activityRoot.getChildAt(i));
        }
        for (View view : views) {
            activityRoot.removeView(view);
            activityWrapper.addView(view);
        }
        activityGestureWrapper.addView(activityWrapper);
        activityRoot.addView(activityGestureWrapper);

        //Creates the menu overlay
        overlay = new RelativeLayout(activity);
        overlay.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        overlay.setBackgroundColor(Color.parseColor("#000000"));
        overlay.setAlpha(0f);
        overlay.setVisibility(View.INVISIBLE);
        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuShowing) {
                    toggle();
                }
            }
        });
        activityRoot.addView(overlay);

        //Adds the menu to the activity rootview
        menu = (ViewGroup) activity.getLayoutInflater().inflate(menuLayoutId, null, false);
        menu.setLayoutParams(new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
        activityRoot.addView(menu);

        menu.setTranslationX(type == LeftMenuType.LEFT ? -width : screenWidth);
        menuShowing = false;

        activityGestureWrapper.setSlideEnabled(swipeEnabled);
        activityGestureWrapper.setMenu(this);
        activityGestureWrapper.setSlidePercentage(swipePercentage);
    }

    public void handleDrag(float x) {
        if(x >= 0 && x < width){
            activityWrapper.setTranslationX(x);
            menu.setTranslationX((-width) + x);
            overlay.setVisibility(View.VISIBLE);
            overlay.setAlpha((x / screenWidth));
//            L.og("x: " + x);
            L.og("alpha: " + (x / screenWidth));
        }
    }

    public void cancelDrag(float x) {
        if (x >= width/2){
            openMenu();
        }else{
            closeMenu();
        }
    }

    public void toggle() {
        if (menuShowing) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    private void openMenu() {
        menuShowing = true;
        AnimatorSet set = new AnimatorSet();
        set.setDuration(250);
        ObjectAnimator menuAnim;
        ObjectAnimator contentAnim;
        ObjectAnimator overlayAnim;
        overlay.setVisibility(View.VISIBLE);
        contentAnim = ObjectAnimator.ofFloat(activityWrapper, "translationX", type == LeftMenuType.LEFT ? width : -width);
        menuAnim = ObjectAnimator.ofFloat(menu, "translationX", type == LeftMenuType.LEFT ? 0 : screenWidth - width);
        overlayAnim = ObjectAnimator.ofFloat(overlay, "alpha", fadeOverlayEnabled ? 0.6f : 0f);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (menuStateListener != null) {
                    menuStateListener.opened();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.playTogether(contentAnim, menuAnim, overlayAnim);
        set.start();
    }

    private void closeMenu() {
        menuShowing = false;
        AnimatorSet set = new AnimatorSet();
        set.setDuration(250);
        ObjectAnimator menuAnim;
        ObjectAnimator contentAnim;
        ObjectAnimator overlayAnim;
        contentAnim = ObjectAnimator.ofFloat(activityWrapper, "translationX", 0);
        menuAnim = ObjectAnimator.ofFloat(menu, "translationX", type == LeftMenuType.LEFT ? -width : screenWidth);
        overlayAnim = ObjectAnimator.ofFloat(overlay, "alpha", 0f);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                overlay.setVisibility(View.INVISIBLE);
                if (menuStateListener != null) {
                    menuStateListener.closed();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.playTogether(contentAnim, menuAnim, overlayAnim);
        set.start();
    }

    public boolean isMenuShowing() {
        return menuShowing;
    }
}
