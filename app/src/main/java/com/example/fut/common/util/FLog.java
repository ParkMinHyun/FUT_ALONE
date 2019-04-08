package com.example.fut.common.util;

import android.util.Log;

public class FLog {

    public static void d(String tag, String format, Object... args) {
        String message = (args == null || args.length == 0) ? format : String.format(format, args);
        Log.d(tag, message);
    }

    public static void d(Class clazz, String msg) {
        d(clazz.getSimpleName(), msg);
    }

    public static void d(Class clazz, String format, Object... args) {
        d(clazz.getSimpleName(), format, args);
    }

    public static void e(String tag, String format, Object... args) {
        String message = getMessage(format, args);
        LogError(tag, message);
    }

    public static void e(Class clazz, Throwable throwable) {
        e(clazz.getSimpleName(), throwable);
    }

    public static void e(String tag, Throwable throwable) {
        LogError(tag, Log.getStackTraceString(throwable));
    }

    public static void e(Class clazz, String msg) {
        LogError(clazz.getSimpleName(), msg);
    }

    public static void e(Class clazz, String format, Object... args) {
        String message = getMessage(format, args);
        LogError(clazz.getSimpleName(), message);
    }

    private static void LogError(String tag, String stackTraceString) {
        Log.e(tag, stackTraceString);
    }

    public static void i(String tag, String format, Object... args) {
        String message = getMessage(format, args);
        Log.i(tag, message);
    }

    public static void i(Class clazz, String msg) {
        Log.i(clazz.getSimpleName(), msg, null);
    }

    public static void i(Class clazz, String format, Object... args) {
        String message = getMessage(format, args);
        Log.i(clazz.getSimpleName(), message);
    }

    public static void w(String tag, String format, Object... args) {
        String message = getMessage(format, args);
        Log.w(tag, message);
    }

    public static void w(Class clazz, String msg) {
        Log.w(clazz.getSimpleName(), msg, null);
    }

    public static void w(Class clazz, String format, Object... args) {
        Log.w(clazz.getSimpleName(), String.format(format, args));

    }

    private static String getMessage(String format, Object[] args) {
        return (args == null || args.length == 0) ? format : String.format(format, args);
    }
}
