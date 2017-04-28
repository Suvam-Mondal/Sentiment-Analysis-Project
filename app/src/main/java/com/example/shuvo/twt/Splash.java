package com.example.shuvo.twt;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Shuvo on 4/22/2017.
 */
public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setContentView(R.layout.splash_layout);

        Thread splash = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(1000);

                } catch (Exception e) {
                } finally {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        };
        splash.start();
    }
}
