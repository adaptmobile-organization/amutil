package dk.adaptmobile.amutil.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Various checks for network connection
 */
public class AMNetworkUtil {

    /**
     * Checks if a network connection is available
     * @param context A Context
     * @return True if a network is ready for use
     */
    public static boolean hasActiveNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    /**
     * Checks if a network connection is available or the connection is being established
     * @param context A Context
     * @return True if a network is ready for use or is being established
     */
    public static boolean hasActiveNetworkOrConnecting(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Returns the type of active connection
     * @param context A Context
     * @return 0 if connected to a mobile network. 1 if connected to a WIFI, -1 if connection type cannot be determined.
     */
    public static int getConnectionType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        switch(activeNetwork.getType()) {
            case ConnectivityManager.TYPE_MOBILE:
                return 0;
            case ConnectivityManager.TYPE_WIFI:
                return 1;
            default:
                return -1;
        }
    }
}
