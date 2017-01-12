package dk.adaptmobile.amutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.File;
import java.io.IOException;

import dk.adaptmobile.amutil.misc.AMImageUtil;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by bjarkeseverinsen on 06/01/2017.
 */

//See this link for guidance setting up powermock with robolectric
// https://github.com/robolectric/robolectric/wiki/Using-PowerMock

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest=Config.NONE)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest(Environment.class)
public class AMImageUtilMockTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    private Context context;
    private File file;
    private String testBitmapName;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;

        PowerMockito.mockStatic(Environment.class);
        file = new File("src/test/resources/");
        PowerMockito.when(Environment.getExternalStorageDirectory()).thenReturn(file);

        //does not save in external dir but one dir higher if slash isn't added
        testBitmapName = "/savebitmaptestimagemock.png";
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
