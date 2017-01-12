package dk.adaptmobile.amutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import dk.adaptmobile.amutil.misc.AMUtil;

/**
 * Created by bjarkeseverinsen on 12/01/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest=Config.NONE)
public class AMUtilTest {

    private Context context;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;
    }

    @Test
    public void isFirstLaunchTest() {
        boolean firstLaunch = AMUtil.isFirstLaunch(context);
        assertTrue(firstLaunch);

        final String FIRST_LAUNCH = "AM_FIRST_LAUNCH";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(FIRST_LAUNCH, false).commit();

        firstLaunch = AMUtil.isFirstLaunch(context);
        assertFalse(firstLaunch);


    }


}
