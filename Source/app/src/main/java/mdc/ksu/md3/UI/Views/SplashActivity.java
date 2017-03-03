package mdc.ksu.md3.UI.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseUser;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(gotoLoginActivity, 2000);

    }

    private Runnable gotoLoginActivity = new Runnable()
    {
        public void run()
        {
            //TODO.. remove TRUE when parse implemented
            if (ParseUser.getCurrentUser().isAuthenticated())
            {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
