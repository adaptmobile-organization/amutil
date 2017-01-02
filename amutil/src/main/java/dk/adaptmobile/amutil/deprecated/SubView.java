package dk.adaptmobile.amutil.deprecated;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

/**
 * View class for building the UI
 * @deprecated Use conductor instead
 */
@Deprecated
public abstract class SubView {

    protected Context context;
    protected ViewContainer container;
    protected View rootView;

    public SubView(Context context, ViewContainer container) {
        this.context = context;
        this.container = container;
    }

    protected void inflate(int layout) {
        rootView = LayoutInflater.from(context).inflate(layout, null, false);
    }

    public void setContainer(ViewContainer container) {
        this.container = container;
    }

    public abstract boolean canGoBack();

    public View getView() {
        return rootView;
    }

    public abstract void didAppear(Intent result);
    public abstract void willAppear();
    public abstract void willDisappear();
}
