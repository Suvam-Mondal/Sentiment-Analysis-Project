package com.example.shuvo.twt;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Shuvo on 4/11/2017.
 */
public class Enter_Search extends AppCompatActivity implements View.OnClickListener {

    EditText t1;
    Button b1;
    String fetch;
    String choice;
    int storedSearchID;
    ListView lv;
    HashMap<Integer,String> storedSearches = new HashMap<Integer,String>();
    HashMap<Integer,String> storedSearchesPolitics = new HashMap<Integer,String>();

    String[] searchList;
    int j=1,id;

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
        lv = (ListView)findViewById(R.id.lv);
        b1.setOnClickListener(this);
        if (choice.equals("movies")) {
         //   Toast.makeText(this, "movies", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            storedSearchID = sharedPreferences.getInt("id", 0);
            // String i=""; i += storedSearchID;
            String[] s = new String[5];
            String name1 = sharedPreferences.getString("0", "");
            s[0] = name1;
            String name2 = sharedPreferences.getString("1", "");
            s[1] = name2;
            String name3 = sharedPreferences.getString("2", "");
            s[2] = name3;
            String name4 = sharedPreferences.getString("3", "");
            s[3] = name4;
            String name5 = sharedPreferences.getString("4", "");
            s[4] = name5;

            lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s));
        }
        else if (choice.equals("politics")){
           // Toast.makeText(this, choice, Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            storedSearchID = sharedPreferences.getInt("id2", 20);
            // String i=""; i += storedSearchID;
            String[] s = new String[5];
            String name1 = sharedPreferences.getString("20", "");
            s[0] = name1;
            String name2 = sharedPreferences.getString("21", "");
            s[1] = name2;
            String name3 = sharedPreferences.getString("22", "");
            s[2] = name3;
            String name4 = sharedPreferences.getString("23", "");
            s[3] = name4;
            String name5 = sharedPreferences.getString("24", "");
            s[4] = name5;

            lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s));
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                View v = view;
                t1.setText(lv.getItemAtPosition(position).toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (b1.equals(v))
        {
            fetch=t1.getText().toString();

            if (choice.equals("movies")) {
                storedSearches.put(storedSearchID, fetch);
            }
            else if (choice.equals("politics")){
                storedSearchesPolitics.put(storedSearchID, fetch);

            }


            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (choice.equals("movies")) {
                for (Map.Entry e : storedSearches.entrySet()) {
                    editor.putString(e.getKey().toString(), e.getValue().toString());
                    //Toast.makeText(this, e.getValue().toString(), Toast.LENGTH_SHORT).show();
                }
                storedSearchID++;
                if (storedSearchID > 4)
                    storedSearchID = 0;
                editor.putInt("id", storedSearchID);
                editor.apply();
            }
            else if (choice.equals("politics")){
                for (Map.Entry e : storedSearchesPolitics.entrySet()) {
                    editor.putString(e.getKey().toString(), e.getValue().toString());
                    //Toast.makeText(this, e.getValue().toString(), Toast.LENGTH_SHORT).show();
                }
                storedSearchID++;
                if (storedSearchID > 24)
                    storedSearchID = 20;

                editor.putInt("id2", storedSearchID);
                editor.apply();
            }



                Intent intent = new Intent(Enter_Search.this,TweetActivity.class);
             intent.putExtra("searchQuery",fetch);
            intent.putExtra("choice",choice);
            startActivity(intent);

        }
    }
}
