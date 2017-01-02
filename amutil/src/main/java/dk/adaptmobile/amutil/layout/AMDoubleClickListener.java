package dk.adaptmobile.amutil.layout;

import android.view.View;

public abstract class AMDoubleClickListener implements View.OnClickListener {

    private static final long DOUBLE_CLICK_TIME_DELTA = 300; //milliseconds

    long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long clickTime = System.currentTimeMillis();
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
            onDoubleClick(v);
        } //{
//            onSingleClick(v);
//        }
        lastClickTime = clickTime;
    }

//    public abstract void onSingleClick(View v);
    public abstract void onDoubleClick(View v);
}
