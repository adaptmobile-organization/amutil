package dk.adaptmobile.amutil.misc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by erik on 24/04/15.
 */
public class AMIntentUtil {

    public static boolean isIntentAvailable(Context context, Intent intent) {
        return context.getPackageManager().resolveActivity(intent, 0) != null;
    }

    public static void emailIntent(Context context, String email, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        try {
            if (isIntentAvailable(context, intent)) {
                context.startActivity(Intent.createChooser(intent, "Send Email"));
            }
        } catch (Exception var5) {

        }
    }

    public static void linkToGooglePlay(Context context, String appPackageName) {
        // final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static void callIntent(Context context, String phoneNumber) {
        String uri = "tel:" + phoneNumber.trim();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        if (isIntentAvailable(context, intent)) {
            context.startActivity(intent);
        }
    }

    public static void webPageIntent(Context context, String homepageURL) {
        if (!homepageURL.startsWith("https://") && !homepageURL.startsWith("http://")) {
            homepageURL = "http://" + homepageURL;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(homepageURL));
        if (isIntentAvailable(context, intent)) {
            context.startActivity(intent);
        }
    }

    public static void navigationIntent(Context context, double latitude, double longitude) {
        String uriString = "http://maps.google.com/maps?&daddr=" + latitude + "," + longitude;
        Log.d("startNavigation", uriString);
        Intent navigationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        if (isIntentAvailable(context, navigationIntent)) {
            context.startActivity(navigationIntent);
        }
    }

    public static void cameraIntent(Context context, int takePicID) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // File file = new File(Environment.getExternalStorageDirectory(), "billede.jpg");
        // intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        if (isIntentAvailable(context, intent)) {
            ((Activity) context).startActivityForResult(intent, takePicID);
        }
    }

    public static void galleryIntent(Context context, int galleryID) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        if (isIntentAvailable(context, intent)) {
            ((Activity) context).startActivityForResult(intent, galleryID);
        }
    }

    /**
     * Open another app.
     *
     * @param context     current Context, like Activity, App, or Service
     * @param packageName the full package name of the app to open
     * @return true if likely successful, false if unsuccessful
     */
    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packageName);
            if (i == null) {
                return false;
                //throw new PackageManager.NameNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
