package dk.adaptmobile.amutil.uitest;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by bjarkeseverinsen on 25/01/2017.
 */

public class RecyclerViewInsideRecyclerViewMatcher {


    private final int parentRecyclerViewId;
    private final int childRecyclerViewId;

    public RecyclerViewInsideRecyclerViewMatcher(int parentRecyclerViewId, int childRecyclerViewId) {
        this.parentRecyclerViewId = parentRecyclerViewId;
        this.childRecyclerViewId = childRecyclerViewId;
    }

//    public Matcher<View> atPosition(final int position) {
//        return atPositionOnView(position, -1);
//    }

    public Matcher<View> atPositionOnView(final int parentPosition, final int childPosition, final int targetViewId) {

        return new TypeSafeMatcher<View>() {
            Resources resources = null;
            View parentRecyclerViewViewholderView;
            View childRecyclerViewViewholderView;

            public void describeTo(Description description) {
                String parentIdDescription = Integer.toString(parentRecyclerViewId);
                String childIdDescription = Integer.toString(childRecyclerViewId);
                if (this.resources != null) {
                    try {
                        childIdDescription = this.resources.getResourceName(childRecyclerViewId);
                        parentIdDescription = this.resources.getResourceName(parentRecyclerViewId);
                    } catch (Resources.NotFoundException var4) {
                        parentIdDescription = String.format("%s (resource name not found)", parentRecyclerViewId);
                        childIdDescription = String.format("%s (resource name not found)", childRecyclerViewId);
                    }
                }
                description.appendText("ParentRecyclerView with id: " + parentIdDescription + " at position: " + parentPosition);
                description.appendText("ChildRecyclerView with id: " + childIdDescription + " at position: " + childPosition);
            }

            public boolean matchesSafely(View view) {

                this.resources = view.getResources();

                if (parentRecyclerViewViewholderView == null) {
                    RecyclerView parentRecyclerView = (RecyclerView) view.getRootView().findViewById(parentRecyclerViewId);
                    if (parentRecyclerView != null && parentRecyclerView.getId() == parentRecyclerViewId) {
                        RecyclerView.ViewHolder parentViewHolder = parentRecyclerView.findViewHolderForAdapterPosition(parentPosition);
                        if (parentViewHolder != null) {
                            parentRecyclerViewViewholderView = parentViewHolder.itemView;
                            RecyclerView childRecyclerView = (RecyclerView) parentRecyclerViewViewholderView.findViewById(childRecyclerViewId);
                            if (childRecyclerView != null && childRecyclerView.getId() == childRecyclerViewId) {
                                RecyclerView.ViewHolder childViewHolder = childRecyclerView.findViewHolderForAdapterPosition(childPosition);
                                if (childViewHolder != null) {
                                    childRecyclerViewViewholderView = childViewHolder.itemView;
                                }
                            }
                        }
                    } else {
                        return false;
                    }
                }

                if (targetViewId == -1) {
                    return view == childRecyclerViewViewholderView;
                } else {
                    View targetView = childRecyclerViewViewholderView.findViewById(targetViewId);
                    return view == targetView;
                }
            }
        };
    }

}
