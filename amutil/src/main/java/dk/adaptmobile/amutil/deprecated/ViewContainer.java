package dk.adaptmobile.amutil.deprecated;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import java.util.Stack;

import dk.adaptmobile.amutil.layout.AMLayoutUtil;


/**
 * Manages a stack of SubViews
 * @deprecated Use conductor instead
 */
@Deprecated
public class ViewContainer {
    protected Context context;
    protected Activity activity;
    protected Stack<SubView> stack;
    protected FrameLayout rootView;
    private boolean isAnimating;

    public ViewContainer(Activity activity) {
        this.context = activity;
        this.activity = activity;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        stack = new Stack<>();
        rootView = (FrameLayout) inflater.inflate(dk.adaptmobile.amutil.R.layout.container, null);
    }

    public boolean canGoBack() {
        if (stack != null && stack.size() > 0) {
            boolean canGoBack = stack.peek().canGoBack();
            if (canGoBack && stack.size() > 1) {
                popSubView(AnimationType.Left, null, true);
                return false;
            } else {
                return canGoBack;
            }
        }
        return true;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void pushSubView(SubView view, final boolean asRoot, boolean animated) {
        if(!isAnimating) {
            view.setContainer(this);
            if (!stackIsEmpty()) { //If stack is not empty, call willDisappear on previous view
                SubView oldView = stack.peek();
                oldView.willDisappear();
            }
            stack.push(view);
            View currentView = null;
            if (rootView.getChildCount() > 0) {
                currentView = rootView.getChildAt(0);
            }
            View newView = view.getView();
            rootView.addView(newView);
            if (stack.size() > 1) {
                final View finalCurrentView = currentView;
                if (animated) {

                    newView.setTranslationX(AMLayoutUtil.getScreenWidth(context));
                    AnimatorSet as = new AnimatorSet();
                    ObjectAnimator oa = ObjectAnimator.ofFloat(newView, "translationX", 0f);
                    as.setInterpolator(new DecelerateInterpolator());
                    as.play(oa);
                    if (currentView != null) {
                        ObjectAnimator oa2 = ObjectAnimator.ofFloat(currentView, "translationX", -AMLayoutUtil.getScreenWidth(context));
                        as.playTogether(oa, oa2);
                    }

                    isAnimating = true;
                    as.setDuration(300);
                    as.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            stack.peek().willAppear();
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (finalCurrentView != null) {
                                rootView.removeView(finalCurrentView);
                            }
                            stack.peek().didAppear(null);

                            if (asRoot) {
                                SubView top = stack.peek();
                                stack.clear();
                                stack.push(top);
                            }
                            isAnimating = false;
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    as.start();
                } else {
                    stack.peek().willAppear();
                    if (finalCurrentView != null) {
                        rootView.removeView(finalCurrentView);
                    }
                    stack.peek().didAppear(null);

                    if (asRoot) {
                        SubView top = stack.peek();
                        stack.clear();
                        stack.push(top);
                    }
                }
            } else {
                stack.peek().willAppear();
                stack.peek().didAppear(null);
            }
        }
    }

    public void popSubView(AnimationType animationType, final Intent result, boolean animated) {
        if(!isAnimating && stack.size() > 1) {
            View currentView = stack.peek().getView();
            //Call willdisappear on the subview
            SubView oldView = stack.peek();
            oldView.willDisappear();

            stack.pop();
//          rootView.removeAllViews();

            final View newView = stack.peek().getView();
            newView.setTranslationX(-AMLayoutUtil.getScreenWidth(context));
            rootView.addView(newView);

            AnimatorSet as = new AnimatorSet();
            ObjectAnimator oa = ObjectAnimator.ofFloat(currentView, "translationX", AMLayoutUtil.getScreenWidth(context));
            as.play(oa);
            if (currentView != null) {
                ObjectAnimator oa2 = ObjectAnimator.ofFloat(newView, "translationX", 0f);
                as.playTogether(oa, oa2);
            }
            as.setDuration(animated ? 300 : 0);

            final View finalCurrentView = currentView;
            isAnimating = true;
            as.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    stack.peek().willAppear();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (finalCurrentView != null) {
                        rootView.removeView(finalCurrentView);
                    }
                    stack.peek().didAppear(result);
                    isAnimating = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            as.start();
        }
    }

    public View getRootView() {
        return rootView;
    }


    public void popToRoot(AnimationType animationType, Intent result, boolean animated) {
        while (stack.size() > 2) {
            stack.remove(1);
        }
        popSubView(animationType, result, animated);

    }


    public int getStackSize() {
        if (stack != null) {
            return stack.size();
        }
        return 0;
    }

    private boolean stackIsEmpty() {
        if(stack.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void clearStack() {
        stack.clear();
    }

    public Stack<SubView> getStack() {
        return stack;
    }


    public void setStack(Stack<SubView> stack) {
        this.stack = stack;
    }

    public enum AnimationType {
        Left, Down, Right, Up
    }
}
