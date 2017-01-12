package dk.adaptmobile.amutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import dk.adaptmobile.amutil.misc.AMImageUtil;

/**
 * Created by bjarkeseverinsen on 06/01/2017.
 */

//See this link for guidance setting up powermock with robolectric
// https://github.com/robolectric/robolectric/wiki/Using-PowerMock

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest=Config.NONE)
public class AMImageUtilTest {

    private Context context;
    private String testBitmapName;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;

        //does not save in external dir but one dir higher if slash isn't added
        testBitmapName = "/savebitmaptestimage.png";
    }

    @Test
    public void loadBitmapFromViewTest() {
        View view = createTestView();
        Bitmap generatedBitmap = AMImageUtil.loadBitmapFromView(view);

        assertNotNull(generatedBitmap);
    }

    @Test
    public void saveBitmapToExtDirTest() throws IOException {
        View view = createTestView();
        Bitmap generatedBitmap = AMImageUtil.loadBitmapFromView(view);

        File savedFile = AMImageUtil.saveBitmapToExtDir(generatedBitmap, testBitmapName);

        assertTrue(savedFile.exists());
    }

    @Test
    public void readBitmapFromExtDirTest() {
        //Test that it can be read from external (mocked dir)
        Bitmap readBitmap = AMImageUtil.readBitmapFromExtDir(testBitmapName);
        assertNotNull(readBitmap);
    }

    @Test
    public void getBitmapFromURLTest() {
        Bitmap bitmap = AMImageUtil.getBitmapFromURL("http://adaptmobile.dk/profiles/amobile2/themes/custom/amobile2_theme/logo.png");
        assertNotNull(bitmap);
    }

    private View createTestView() {
        LinearLayout view = new LinearLayout(context);
        view.setOrientation(LinearLayout.VERTICAL);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        TextView tv1 = new TextView(context);
        tv1.setText("HELLO");
        view.addView(tv1);

        TextView tv2 = new TextView(context);
        tv2.setText("WORLD");
        view.addView(tv2);
        return view;
    }

}
