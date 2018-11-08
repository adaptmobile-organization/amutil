package dk.adaptmobile.amutil;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import java.util.List;
import dk.adaptmobile.amutil.system.L;
import static junit.framework.Assert.*;

/**
 * Created by bjarkeseverinsen on 12/01/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest=Config.NONE)
public class AMLTest {

    @Test
    public void ogMessageTest() throws Exception {
        L.og("message");

        List<ShadowLog.LogItem> logs = ShadowLog.getLogs();

        ShadowLog.LogItem message = logs.get(0);
        assertEquals(Log.DEBUG, message.type);
        assertEquals(this.getClass().getName(), message.tag);
        assertEquals("message", message.msg);
    }

    @Test
    public void ogNumberTest() throws Exception {
        L.og(0);

        List<ShadowLog.LogItem> logs = ShadowLog.getLogs();

        ShadowLog.LogItem number = logs.get(0);
        assertEquals(Log.DEBUG, number.type);
        assertEquals(this.getClass().getName(), number.tag);
        assertEquals("0", number.msg);
    }

    @Test
    public void ogTagMessageTest() throws Exception {
        L.og("tag", "message");

        List<ShadowLog.LogItem> logs = ShadowLog.getLogs();

        ShadowLog.LogItem tagMessage = logs.get(0);
        assertEquals(Log.DEBUG, tagMessage.type);
        assertEquals("tag", tagMessage.tag);
        assertEquals("message", tagMessage.msg);
    }

    @Test
    public void ogTagNumberTest() throws Exception {
        L.og("tag", 0);

        List<ShadowLog.LogItem> logs = ShadowLog.getLogs();

        ShadowLog.LogItem tagNumber = logs.get(0);
        assertEquals(Log.DEBUG, tagNumber.type);
        assertEquals("tag", tagNumber.tag);
        assertEquals("0", tagNumber.msg);
    }

    @Test
    public void ogNullTest() throws Exception {
        L.og(null);
        List<ShadowLog.LogItem> logs = ShadowLog.getLogs();

        ShadowLog.LogItem message = logs.get(0);
        assertEquals(Log.DEBUG, message.type);
        assertEquals(this.getClass().getName(), message.tag);
        assertEquals("null", message.msg);
    }

    @Test
    public void ogTagNullTest() throws Exception {
        L.og(null, null);

        List<ShadowLog.LogItem> logs = ShadowLog.getLogs();

        ShadowLog.LogItem tagNumber = logs.get(0);
        assertEquals(Log.DEBUG, tagNumber.type);
        assertEquals("null", tagNumber.tag);
        assertEquals("null", tagNumber.msg);
    }


}
