package com.example.shuvo.twt;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Shuvo on 4/20/2017.
 */
public class Topic extends AppCompatActivity implements View.OnClickListener {

    Button movies,politics;
    String s1="movies",s2="politics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.topic_layout);

        movies=(Button)findViewById(R.id.movies);
        politics=(Button)findViewById(R.id.politics);

        movies.setOnClickListener(this);

        politics.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (movies.equals(v))
        {
            Intent i = new Intent(Topic.this,Enter_Search.class);
            i.putExtra("choice",s1);
            startActivity(i);

        }

        if (politics.equals(v))
        {
            Intent j = new Intent(Topic.this,Enter_Search.class);
            j.putExtra("choice",s2);
            startActivity(j);

        }
    }



}
