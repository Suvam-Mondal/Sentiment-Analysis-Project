package com.example.shuvo.twt;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Shuvo on 4/11/2017.
 */
public class Enter_Search extends AppCompatActivity implements View.OnClickListener {

    EditText t1;
    Button b1;
    String fetch;
    String choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.entersearch_layout);

        Bundle p = getIntent().getExtras();
        choice = p.getString("choice");

        b1 = (Button)findViewById(R.id.button);
        t1= (EditText) findViewById(R.id.textView);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (b1.equals(v))
        {
            fetch=t1.getText().toString();
            Intent intent = new Intent(Enter_Search.this,TweetActivity.class);
             intent.putExtra("searchQuery",fetch);
            intent.putExtra("choice",choice);
            startActivity(intent);
        }
    }
}
