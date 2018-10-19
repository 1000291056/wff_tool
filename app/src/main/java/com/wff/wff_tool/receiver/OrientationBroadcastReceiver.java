package com.wff.wff_tool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.Toast;

public class OrientationBroadcastReceiver extends BroadcastReceiver {
    //An integer that holds the value of the orientation given by the current configuration
    private int configOrientation;

    //A WindowManager object that will act as a handle to the window service
    private WindowManager wm;
    //An integer that holds the value of the orientation (in degrees) given by the window service
    private int windowServOrientation;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Get the orientation from the current configuration object
        configOrientation = context.getResources().getConfiguration().orientation;

        //Display the current orientation using a Toast notification
        switch (configOrientation) {
            case Configuration.ORIENTATION_LANDSCAPE: {
                Toast.makeText(context, "Orientation is LANDSCAPE", Toast.LENGTH_SHORT).show();
                break;
            }
            case Configuration.ORIENTATION_PORTRAIT: {
                Toast.makeText(context, "Orientation is PORTRAIT", Toast.LENGTH_SHORT).show();
                break;
            }
            case Configuration.ORIENTATION_SQUARE: {
                Toast.makeText(context, "Orientation is SQUARE", Toast.LENGTH_SHORT).show();
                break;
            }

            case Configuration.ORIENTATION_UNDEFINED:
            default: {
                Toast.makeText(context, "Orientation is UNDEFINED", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        //Get a handle to the Window Service
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //Query the current orientation made available by the Window Service
        //The getOrientation() method is deprecated. Instead, use getRotation() when targeting Android API 8 (Android 2.2 - Froyo) or above.
        windowServOrientation = wm.getDefaultDisplay().getOrientation();

        //Display the current orientation using a Toast notification
        switch (windowServOrientation) {
            case Surface.ROTATION_0: {
                Toast.makeText(context, "Rotation is 0 degrees.", Toast.LENGTH_SHORT).show();
                break;
            }

            case Surface.ROTATION_90: {
                Toast.makeText(context, "Rotation is 90 degrees.", Toast.LENGTH_SHORT).show();
                break;
            }

            case Surface.ROTATION_180: {
                Toast.makeText(context, "Rotation is 180 degrees.", Toast.LENGTH_SHORT).show();
                break;
            }

            case Surface.ROTATION_270: {
                Toast.makeText(context, "Rotation is 270 degrees.", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}