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

        //TODO.. Insert Parse Info
        // APP_ID, CLIENT_ID
        Parse.initialize(new Parse.Configuration.Builder(this)
                                 .applicationId(getString(R.string.APP_KEY))
                                 .clientKey(getString(R.string.CLIENT_KEY))
                                 .server(getString(R.string.SERVER_KEY)) // The trailing slash is important.
                                 .build()
        );

        ParseUser.enableAutomaticUser();
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
