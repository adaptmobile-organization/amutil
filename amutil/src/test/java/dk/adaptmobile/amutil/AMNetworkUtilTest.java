package dk.adaptmobile.amutil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowConnectivityManager;
import org.robolectric.shadows.ShadowNetworkInfo;

import dk.adaptmobile.amutil.network.AMNetworkUtil;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by bjarkeseverinsen on 12/01/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest=Config.NONE)
public class AMNetworkUtilTest {

    private Context context;

    private ConnectivityManager connectivityManager;
    private ShadowConnectivityManager shadowConnectivityManager;
    private ShadowNetworkInfo shadowOfActiveNetworkInfo;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;
        connectivityManager = (ConnectivityManager) RuntimeEnvironment.application.getSystemService(Context.CONNECTIVITY_SERVICE);
        shadowConnectivityManager = Shadows.shadowOf(connectivityManager);
        shadowOfActiveNetworkInfo = Shadows.shadowOf(connectivityManager.getActiveNetworkInfo());
    }

    @Test
    public void hasActiveNetworkTest() {
        boolean hasActiveNetwork = AMNetworkUtil.hasActiveNetwork(context);
        assertTrue(hasActiveNetwork);

        //disable wifi
        setConnectivity(false, false);

        hasActiveNetwork = AMNetworkUtil.hasActiveNetwork(context);
        assertFalse(hasActiveNetwork);
    }

    @Test
    public void hasActiveNetworkOrConnectingTest() {
        boolean hasActiveNetworkOrConnecting = AMNetworkUtil.hasActiveNetworkOrConnecting(context);
        assertTrue(hasActiveNetworkOrConnecting);

        setConnectivity(false, false);

        hasActiveNetworkOrConnecting = AMNetworkUtil.hasActiveNetworkOrConnecting(context);
        assertFalse(hasActiveNetworkOrConnecting);
    }

    @Test
    public void getConnectionTypeTest() {
        setConnectionType(ConnectivityManager.TYPE_WIFI);
        assertEquals(ConnectivityManager.TYPE_WIFI, AMNetworkUtil.getConnectionType(context));

        setConnectionType(ConnectivityManager.TYPE_MOBILE);
        assertEquals(ConnectivityManager.TYPE_MOBILE, AMNetworkUtil.getConnectionType(context));

        setConnectionType(ConnectivityManager.TYPE_BLUETOOTH);
        assertEquals(-1, AMNetworkUtil.getConnectionType(context));
    }

    private void setConnectivity(boolean isAvailable, boolean isConnected) {
//        NetworkInfo networkInfo = ShadowNetworkInfo.newInstance(NetworkInfo.DetailedState.CONNECTED, ConnectivityManager.TYPE_WIFI, 0, isAvailable, isConnected);
//        shadowConnectivityManager.setActiveNetworkInfo(networkInfo);
        shadowOfActiveNetworkInfo.setAvailableStatus(isAvailable);
        shadowOfActiveNetworkInfo.setConnectionStatus(isConnected);
    }

    private void setConnectionType(int type) {
        shadowOfActiveNetworkInfo.setConnectionType(type);
    }
}


