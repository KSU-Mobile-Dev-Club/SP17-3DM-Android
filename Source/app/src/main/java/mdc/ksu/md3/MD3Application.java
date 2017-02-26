package mdc.ksu.md3;

import android.app.Application;

import com.parse.Parse;

public class MD3Application extends Application
{

    private static MD3Application aInstance;

    public MD3Application()
    {
        aInstance = this;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        //TODO.. Insert Parse Info
        Parse.initialize(this, "APPLICATION_ID", "CLIENT_KEY");

    }

    public static synchronized MD3Application getInstance()
    {
        return aInstance;
    }
}
