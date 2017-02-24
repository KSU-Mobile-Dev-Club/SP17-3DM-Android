package mdc.ksu.md3;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by amgregoi on 2/23/17.
 */

public class MD3Application extends Application
{

    private static MD3Application aInstance;

    /***
     * TODO..
     */
    public MD3Application()
    {
        aInstance = this;
    }

    /***
     * TODO..
     */
    @Override
    public void onCreate()
    {
        super.onCreate();

        //TODO.. Insert Parse Info
        Parse.initialize(this, "APPLICATION_ID", "CLIENT_KEY");

    }

    /***
     * TODO..
     * @return
     */
    public static synchronized MD3Application getInstance()
    {
        return aInstance;
    }
}
