package in.ashu.runtimepermission.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by Ashu on 11/29/2015.
 */
public class PermissionUtil {
    private static final String PERMISSION_UTIL_TAG = "PermissionUtils";
    private static int PERMISSION_REQUEST_UTILS_CODE;
    private static Context utilsContext;
    private static Boolean value;

    // Permission Checks ( is granted or not)
    public static void checkForPermission(@NonNull Activity activity, @NonNull Context context, @NonNull String permission,
                                          @NonNull int PERMISSION_REQUEST_CODE) {

        PERMISSION_REQUEST_UTILS_CODE = PERMISSION_REQUEST_CODE;
        utilsContext = context;
        if (ActivityCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available
            Log.i(PERMISSION_UTIL_TAG, "Permission is already available");
            value = true;
            performWork();
            //Toast.makeText(context,"HERE",Toast.LENGTH_SHORT).show();
        } else {
            // Permission is missing and must be requested.
            requestPermission(activity, permission, PERMISSION_REQUEST_CODE);
        }
    }

    // Requesting for Permission.
    public static void requestPermission(@NonNull Activity activity, @NonNull String permission,
                                         @NonNull int PERMISSION_REQUEST_CODE) {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

            // Request the permission
            Log.i(PERMISSION_UTIL_TAG, "Requesting Permission ");
            ActivityCompat.requestPermissions(activity, new String[]{permission}, PERMISSION_REQUEST_CODE);

        } else {
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(activity, new String[]{permission}, PERMISSION_REQUEST_CODE);
        }
    }

    public static Boolean performWork() {

        if ( value ) {
            Log.i(PERMISSION_UTIL_TAG, "Perform work");
            return true;
        } else
            return false;
    }

}

