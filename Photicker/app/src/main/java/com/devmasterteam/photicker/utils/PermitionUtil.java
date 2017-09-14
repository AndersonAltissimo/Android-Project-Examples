package com.devmasterteam.photicker.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.devmasterteam.photicker.R;
import com.devmasterteam.photicker.views.MainActivity;

/**
 * Created by anderson on 13/09/17.
 */

public class PermitionUtil {
    public static final int CAMERA_PERMISSION = 0;

    public static boolean hasCameraPermition(Context context) {
        if (needToAskPermition()){
            return ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private static boolean needToAskPermition() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public static void asksCameraPermitions(final Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)){
            new AlertDialog.Builder(activity).setMessage(activity.getString(R.string.permission_camera_explanation));
            new AlertDialog.Builder(activity).setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(activity, new String[] {
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                            PermitionUtil.CAMERA_PERMISSION); }
            }).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[] {
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                        PermitionUtil.CAMERA_PERMISSION);
        }
    }
}
