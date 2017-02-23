package mdc.ksu.md3.Utils;

import android.util.Log;

public class MD3Logger
{
    private final static String TAG = MD3Logger.class.getSimpleName();
    private final static boolean mDEBUG = false;


    public static void LogDebug(String aClass, String aFunction, String aMessage)
    {
        if (mDEBUG)
        {
            Log.i(TAG + "__DEBUG", aClass + " >> " + aFunction + "() > " + aMessage);
        }
    }

    public static void LogInfo(String aClass, String aFunction, String aMessage)
    {
        Log.i(TAG + "__INFO", aClass + " >> " + aFunction + "() > " + aMessage);
    }

    public static void LogWarn(String aClass, String aFunction, String aMessage)
    {
        Log.i(TAG + "__WARN", aClass + " >> " + aFunction + "() > " + aMessage);
    }
}
