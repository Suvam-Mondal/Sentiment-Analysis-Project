package com.example.shuvo.twt;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;
import com.twitter.sdk.android.tweetui.TweetViewAdapter;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Shuvo on 4/11/2017.
 */
public class TweetActivity extends Activity implements View.OnClickListener {
    //user search setup
    private boolean flagloading;
    private boolean endofsearchResult;
    private static String Search_query;
    private TweetViewAdapter adapter;
    private  static final  String Search_result_type = "recent";
    private static final int Search_count = 1000;
    private long maxId;
    ListView SearchList;
    Button button;
    String fetchChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tweetactivity_layout);

        button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(this);

        Bundle p = getIntent().getExtras();
        String fetchQ = p.getString("searchQuery");
        fetchChoice = p.getString("choice");
        Toast.makeText(TweetActivity.this,"You searched for :"+fetchQ, Toast.LENGTH_SHORT).show();
        Search_query = fetchQ;
        setProgressBarIndeterminateVisibility(true);

        adapter = new TweetViewAdapter(TweetActivity.this);
        SearchList = (ListView) findViewById(R.id.tweet_search);
        SearchList.setAdapter(adapter);
        SearchList.setEmptyView(findViewById(R.id.loading));

        final SearchService service = Twitter.getApiClient().getSearchService();
        service.tweets(Search_query,null,null,null,Search_result_type,Search_count,null,null,maxId,true, new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                setProgressBarIndeterminateVisibility(false);
                final List<Tweet> tweets = result.data.tweets;
                adapter.getTweets().addAll(tweets);
                adapter.notifyDataSetChanged();
                try {
                    int c=0;
                    String str;
                    File file = new File(getBaseContext().getFilesDir(), "myFile.txt");
                    str = getFilesDir().toString();
                    FileOutputStream outputStream;
                    outputStream = openFileOutput("myFile.txt", Context.MODE_PRIVATE);
                    String newline="\n";
                    for (Tweet tw : tweets) {

                        String z;
                        z= tw.text.replaceAll("\n"," ");
                        if (!(z.substring(0,3).equals("RT "))) {
                            outputStream.write(z.getBytes());
                            outputStream.write(newline.getBytes());
                            c++;
                        }

                       // outputStream.write(tw.text.toString().getBytes());
                       // outputStream.write(newline.getBytes());
                        //Toast.makeText(twitterSearch.this, tw.text, Toast.LENGTH_SHORT).show();
                    }
                   // Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT).show();
                    outputStream.close();
                }
                catch (IOException e){}
                if (tweets.size()>0)
                {
                    maxId = tweets.get(tweets.size()-1).id-1;
                }
                else {
                    endofsearchResult = true;
                }
                flagloading=false;
            }

            @Override
            public void failure(TwitterException exception) {
                setProgressBarIndeterminateVisibility(false);
                Toast.makeText(TweetActivity.this, "Failed to retrieve tweets", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (button.equals(v))
        {
             Intent i = new Intent(TweetActivity.this, test.class);
            i.putExtra("choice",fetchChoice);
            //ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v,0,0,v.getWidth(),v.getHeight());
            startActivity(i);
        }
    }
}

