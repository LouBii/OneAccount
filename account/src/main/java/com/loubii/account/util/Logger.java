package com.loubii.account.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author luo
 * @date 2017/8/18
 */
public class Logger {
    public static final String TAG = "xywallet";
    public static final boolean DEBUG = true;

    public static void toast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void debug(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void debug(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String tag, String error) {

        if (DEBUG) {

            Log.e(tag, error);
        }
    }

    public static void e(String error) {

        if (DEBUG) {

            Log.e(TAG, "------->" + error);
        }
    }
}
