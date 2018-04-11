package com.ikechukwuakalu.med_manager.utils;

import android.util.Log;

/**
 * Wrapper Class for the Android Log API
 */
public class Logger {

    private static final String TAG = "Med-Logger";

    public static void debug(String message, boolean debug) {
        if (! debug) return;
        Log.d(TAG, message);
    }

    public static void warn(String message, boolean debug) {
        if (! debug) return;
        Log.w(TAG, message);
    }

    public static void error(String message, boolean debug) {
        if (! debug) return;
        Log.e(TAG, message);
    }

    public static void verbose(String message, boolean debug) {
        if (! debug) return;
        Log.v(TAG, message);
    }

    public static void info(String message, boolean debug) {
        if (! debug) return;
        Log.i(TAG, message);
    }

    public static void wtf(String message, boolean debug) {
        if (! debug) return;
        Log.wtf(TAG, message);
    }
}