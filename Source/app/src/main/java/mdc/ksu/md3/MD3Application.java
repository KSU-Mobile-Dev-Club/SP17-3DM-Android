package mdc.ksu.md3;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

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

        Parse.Configuration.Builder lBuilder = new Parse.Configuration.Builder(this);
        lBuilder.applicationId(getString(R.string.APP_KEY));
        lBuilder.server(getString(R.string.SERVER_KEY));
        lBuilder.enableLocalDataStore();

        Parse.initialize(lBuilder.build());

        ParseACL defaultACL = new ParseACL();

        defaultACL.setPublicReadAccess(true);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseACL.setDefaultACL(defaultACL, true);

    }

    public static synchronized MD3Application getInstance()
    {
        return aInstance;
    }
}
