package dk.adaptmobile.amutil.io;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Containes helper methods for working with files on Android
 */
public class AMFileUtil {
    //TODO - Is this needed? When would we use this?
    public static String getBasePath(Context context, boolean trailingSlash) {
        String dir = context.getFilesDir().getAbsolutePath();
        if (trailingSlash) {
            return dir + "/";
        } else {
            return dir;
        }
    }

    /**
     * Loads a json file from the assets folder
     * @param context A context
     * @param path Path to the json file
     * @return A string with the content of the file, or an empty string if no file was found
     * @throws IOException
     */
    public static String loadJSONFromAsset(Context context, String path) throws IOException {
        if(context == null || path == null) {
            return "";
        }
        InputStream is = context.getAssets().open(path);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, "UTF-8");
    }

    /**
     * Returns a file from internal storage
     * @param context A context
     * @param path Path of the file
     * @return A file object pointing to the file, otherwise null
     */
    public static File getInternalFile(Context context, String path) {
        if(path == null) { //Handle a null value
            return null;
        }
        File file = new File(context.getFilesDir() + File.separator + path);
        if(file.exists()) {
            return file;
        } else {
            return null;
        }

    }

    /**
     * Returns a file from external storage
     * @param path Path of the file
     * @return A file object pointing to the file, otherwise null
     */
    public static File getExternalFile(String path) {
        if(path == null) { //Handle a null value
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + path);
        if(file.exists()) {
            return file;
        } else {
            return null;
        }

    }

    /**
     * Copies a file from one location to another useing I/O streams
     * @param input The file to be copied
     * @param output The location to copy the file
     * @return True if copy was successful, otherwise false
     * @throws IOException
     */
    public static boolean copyInternalFile(File input, File output) throws IOException {
        if(input == null || output == null) {
            return false;
        }
        InputStream in = new FileInputStream(input);
        OutputStream out = new FileOutputStream(output);
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        return true;
    }

    /**
     * Deletes a file
     * @param file The file object to be deleted
     * @return True if delete was successful, otherwise false
     */
    public static boolean removeFile(File file) {
        return file != null && file.delete();
    }
}
