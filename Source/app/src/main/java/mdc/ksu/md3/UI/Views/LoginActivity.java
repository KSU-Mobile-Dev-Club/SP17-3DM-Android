package mdc.ksu.md3.UI.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdc.ksu.md3.R;

public class LoginActivity extends AppCompatActivity
{
    @BindView(R.id.username_text) EditText mUserName;
    @BindView(R.id.password_text) EditText mPassword;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in)
    public void signInButton()
    {
        parseSignIn();
    }

    @OnClick(R.id.sign_up)
    public void signUpButton()
    {
        parseSignUp();
    }

    private void startMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void parseSignIn()
    {
        if (!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser()))
        {
            startMainActivity();
        }
        else
        {
            String lUsername = mUserName.getText().toString();
            String lPassword = mPassword.getText().toString();
            ParseUser.logInInBackground(lUsername, lPassword,
                                        new LogInCallback()
                                        {
                                            public void done(ParseUser user, ParseException e)
                                            {
                                                if (user != null)
                                                {
                                                    startMainActivity();
                                                }
                                                else
                                                {
                                                    sendToast("No such user exist, please signup");
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
        }
    }

    private void parseSignUp()
    {
        String lUsername = mUserName.getText().toString();
        String lPassword = mUserName.getText().toString();

        // Force user to fill up the form
        if (lUsername.equals("") && lPassword.equals(""))
        {
            sendToast("Please complete the sign up form");
        }
        else
        {
            ParseUser lUser = new ParseUser();
            lUser.setUsername(lUsername);
            lUser.setPassword(lPassword);
            lUser.signUpInBackground(new SignUpCallback()
            {
                public void done(ParseException e)
                {
                    if (e == null)
                    {
                        sendToast("Successfully Signed up, please log in.");
                    }
                    else
                    {
                        sendToast("Sign up Error");
                    }
                }
            });
        }

    }

    private void sendToast(String aMessage)
    {
        Toast.makeText(getApplicationContext(), aMessage, Toast.LENGTH_LONG).show();
    }

    public Context getContext()
    {
        return this;
    }

}
