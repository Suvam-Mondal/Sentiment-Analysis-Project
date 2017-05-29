package com.example.shuvo.twt;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {

    private static final String TWITTER_KEY = "s5m8Et0hiUPhCxMlMDofpwGtb"; // Use your twitter Consumer Key
    private static final String TWITTER_SECRET = "14pwt8O6Z0WZJFJMhLddu0PnX1qe0yhB33xAEi0FfrMULlDLVQ"; // Use your twitter Secret Key
    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        InputStream databaseInputStream1 = getResources().openRawResource(R.raw.imdb_labelled);
        try {
            File file = new File(getBaseContext().getFilesDir(), "imdb_labelled.txt");
            OutputStream out= openFileOutput("imdb_labelled.txt", Context.MODE_PRIVATE);
            IOUtils.copy(databaseInputStream1, out);
            databaseInputStream1.close();
            out.close();
        }
        catch (IOException e){}

        InputStream databaseInputStream2 = getResources().openRawResource(R.raw.positivekeywords);
        try {
            File file = new File(getBaseContext().getFilesDir(), "positivekeywords.txt");
            OutputStream out= openFileOutput("positivekeywords.txt", Context.MODE_PRIVATE);
            IOUtils.copy(databaseInputStream2, out);
            databaseInputStream1.close();
            out.close();
        }
        catch (IOException e){}

        InputStream databaseInputStream3 = getResources().openRawResource(R.raw.politics);
        try {
            File file = new File(getBaseContext().getFilesDir(), "politics.txt");
            OutputStream out= openFileOutput("politics.txt", Context.MODE_PRIVATE);
            IOUtils.copy(databaseInputStream3, out);
            databaseInputStream1.close();
            out.close();
        }
        catch (IOException e){}


        InputStream databaseInputStream7 = getResources().openRawResource(R.raw.negetivekeywords);
        try {
            File file = new File(getBaseContext().getFilesDir(), "negetivekeywords.txt");
            OutputStream out= openFileOutput("negetivekeywords.txt", Context.MODE_PRIVATE);
            IOUtils.copy(databaseInputStream7, out);
            databaseInputStream7.close();
            out.close();
        }
        catch (IOException e){}

        // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setText("Login");
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,Topic.class);
                startActivity(intent);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

}
