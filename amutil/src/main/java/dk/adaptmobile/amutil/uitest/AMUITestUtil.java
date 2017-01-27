package dk.adaptmobile.amutil.uitest;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.PickerActions.setDate;
import static android.support.test.espresso.contrib.PickerActions.setTime;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static dk.adaptmobile.amutil.uitest.TestUtils.withChildRecyclerView;
import static dk.adaptmobile.amutil.uitest.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by bjarkeseverinsen on 26/01/2017.
 */

public class AMUITestUtil {

    public static ViewInteraction findViewById(int id) {
        return onView(allOf(withId(id)));
    }

    public static ViewInteraction findViewByIdOnPositionInRecyclerView(int recyclerViewId, int position, int viewId) {
        return onView(withRecyclerView(recyclerViewId).atPositionOnView(position, viewId));
    }

    public static ViewInteraction clickView(int id) {
        ViewInteraction view = findViewById(id);
        clickView(view);
        return view;
    }

    public static void clickView(ViewInteraction view) {
        view.perform(click());
    }

    public static void typeTextInView(ViewInteraction view, String text, boolean closeKeyboardAfterwards) {
        //ESPRESSO DOES NOT HANDLE Æ, Ø, Å, use replaceText instead if you HAFT TO test words with these chars
        if (closeKeyboardAfterwards) {
            view.perform(typeText(text), closeSoftKeyboard());
        } else {
            view.perform(typeText(text));
        }
    }

    public static void typeTextInView(int viewId, String text, boolean closeKeyboardAfterwards) {
        //ESPRESSO DOES NOT HANDLE Æ, Ø, Å, use replaceText instead if you HAFT TO test words with these chars
        ViewInteraction view = findViewById(viewId);
        if (closeKeyboardAfterwards) {
            view.perform(typeText(text), closeSoftKeyboard());
        } else {
            view.perform(typeText(text));
        }
    }

    public static void replaceTextInView(ViewInteraction view, String text) {
        view.perform(replaceText(text));
    }

    public static void closeKeyBoard(ViewInteraction view) {
        view.perform(closeSoftKeyboard());
    }

    public static void pressKeyboardActionButton(ViewInteraction view) {
        view.perform(pressImeActionButton());
    }

    public static ViewInteraction assertView(int id, Matcher... matchers) {
        ViewInteraction view = findViewById(id);
        assertView(view, matchers);
        return view;
    }

    public static void assertView(ViewInteraction view, Matcher... matchers) {
        view.check(matches(allOf(matchers)));
    }

    public static void viewIsDisplayed(ViewInteraction view) {
        view.check(matches(isDisplayed()));
    }

    public static ViewInteraction viewIsDisplayed(int id) {
        ViewInteraction view = findViewById(id);
        viewIsDisplayed(view);
        return view;
    }

    public static void viewIsNotDisplayed(ViewInteraction view) {
        view.check(matches(isNotDisplayed()));
    }

    public static ViewInteraction viewIsNotDisplayed(int id) {
        ViewInteraction view = findViewById(id);
        viewIsNotDisplayed(view);
        return view;
    }

    public static Matcher isNotDisplayed() {
        return not(isDisplayed());
    }

    public static void viewDoesNotExist(ViewInteraction view) {
        view.check(doesNotExist());
    }

    public static ViewInteraction viewDoesNotExist(int id) {
        ViewInteraction view = findViewById(id);
        viewDoesNotExist(view);
        return view;
    }

    public static void viewHasText(ViewInteraction view, int stringResource) {
        view.check(matches(withText(stringResource)));
    }

    public static void viewHasText(ViewInteraction view, String text) {
        view.check(matches(withText(text)));
    }

    public static ViewInteraction viewHasText(int id, int stringResource) {
        ViewInteraction view = findViewById(id);
        viewHasText(view, stringResource);
        return view;
    }

    public static ViewInteraction viewHasText(int id, String text) {
        ViewInteraction view = findViewById(id);
        viewHasText(view, text);
        return view;
    }

    public static ViewInteraction assertOnRecyclerViewItem(int recyclerViewId, int position, int viewId, Matcher... matchers) {
        return onView(withRecyclerView(recyclerViewId).atPositionOnView(position, viewId)).check(matches(allOf(matchers)));
    }

    public static ViewInteraction assertOnChildRecyclerViewItem(int parentRecyclerViewId, int parentPosition, int childRecyclerView, int childPosition, int viewId, Matcher... matchers) {
        return onView(withChildRecyclerView(parentRecyclerViewId, childRecyclerView).atPositionOnView(parentPosition, childPosition, viewId)).check(matches(allOf(matchers)));
    }

    public static ViewInteraction performActionOnChildRecyclerView(int parentRecyclerViewId, int parentPosition, int childRecyclerView, int childPosition, int viewId, ViewAction... actions) {
        return onView(withChildRecyclerView(parentRecyclerViewId, childRecyclerView).atPositionOnView(parentPosition, childPosition, viewId)).perform(actions);
    }

    public static void scrollToPositionInRecyclerView(ViewInteraction recyclerView, int position) {
        recyclerView.perform(RecyclerViewActions.scrollToPosition(position));
    }

    public static void scrollToViewInRecyclerView(ViewInteraction recyclerView, int id) {
        recyclerView.perform(RecyclerViewActions.scrollTo(hasDescendant(withId(id))));
    }

    public static void scrollToViewInRecyclerView(ViewInteraction recyclerView, Matcher... matchers) {
        recyclerView.perform(RecyclerViewActions.scrollTo(hasDescendant(allOf(matchers))));
    }

    public static void performActionOnRecyclerViewPosition(ViewInteraction recyclerView, int position, ViewAction action) {
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition(position, action));
    }

    public static void performActionsOnRecyclerViewPositionItem(int recyclerViewId, int position, int viewId, ViewAction... actions) {
        onView(withRecyclerView(recyclerViewId).atPositionOnView(position, viewId)).perform(actions);
    }

    public static void clickRecyclerViewPositionItem(int recyclerViewId, int position, int viewId) {
        performActionsOnRecyclerViewPositionItem(recyclerViewId, position, viewId, click());
    }

    public static void clickRecyclerViewPosition(ViewInteraction recyclerView, int position) {
        performActionOnRecyclerViewPosition(recyclerView, position, click());
    }

    public static void clickRecyclerViewPosition(int recyclerViewId, int position) {
        ViewInteraction recyclerView = findViewById(recyclerViewId);
        performActionOnRecyclerViewPosition(recyclerView, position, click());
    }

    //make sure datepicker is visible when calling this method
    public static void selectDateInDatepicker(int dayOfMonth, int monthOfYear, int year) {
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }

    public static void selectTimeInTimepicker(int hours, int minutes) {
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(12, 36));
        onView(withId(android.R.id.button1)).perform(click());
    }

}
