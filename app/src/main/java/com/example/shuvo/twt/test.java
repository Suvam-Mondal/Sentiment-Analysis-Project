package com.example.shuvo.twt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.StackedBarChart;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shuvo on 4/19/2017.
 */
public class test extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button chooseAnotherTopic;
    public float f;
    public double d;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.test_layout);

        BarChart chart = (BarChart) findViewById(R.id.chart);
        textView=(TextView)findViewById(R.id.textView2);
        chooseAnotherTopic =(Button)findViewById(R.id.button3);

        chooseAnotherTopic.setOnClickListener(this);

        Bundle p = getIntent().getExtras();
        String choice = p.getString("choice");
        String ss=Main.main(getApplicationContext().getApplicationContext(),choice);

        int length = ss.length();

         f=Float.valueOf(ss);
        BarData data = new BarData(getYAxisValues(), getDataSet());
        chart.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);
        chart.getAxis(YAxis.AxisDependency.LEFT).setAxisMaxValue(100);
        chart.setData(data);
       // YAxis yAxi = chart.getAxis(YAxis.AxisDependency.LEFT);
       // yAxi.setAxisMaxValue(100);
        //chart.getXAxis().setEnabled(false);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();

        String result =ss+"% of the tweets has positive reaction";
        SpannableString res = new SpannableString(result);

        if (length == 5) {
            res.setSpan(new RelativeSizeSpan(2f), 0, 5, 0);
            res.setSpan(new RelativeSizeSpan(1.5f), 25, 33, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 25, 33, 0);
            textView.setText(res);
        }
        else if (length == 6){
            res.setSpan(new RelativeSizeSpan(2f), 0, 6, 0);
            res.setSpan(new RelativeSizeSpan(1.5f), 26, 34, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 26, 34, 0);
            textView.setText(res);
        }
        else if (length == 4){
            res.setSpan(new RelativeSizeSpan(2f), 0, 5, 0);
            res.setSpan(new RelativeSizeSpan(1.5f), 24, 32, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 24, 32, 0);
            textView.setText(res);
        }
        else if (length == 3){
            res.setSpan(new RelativeSizeSpan(2f), 0, 4, 0);
            res.setSpan(new RelativeSizeSpan(1.5f), 23, 31, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 23, 31, 0);
            textView.setText(res);
        }
        //+"% of the tweets has positive reaction"

    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(f, 0); // Jan
        valueSet1.add(v1e1);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Positive");
        barDataSet1.setColor(Color.rgb(0, 155, 0));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);

        return dataSets;
    }
    private  ArrayList<String> getYAxisValues(){
        ArrayList<String> yAxis = new ArrayList<>();
        yAxis.add(" ");

        return yAxis;
    }

    @Override
    public void onClick(View v) {
        if (chooseAnotherTopic.equals(v)){
            Intent goback = new Intent(test.this,Topic.class);
            startActivity(goback);
        }
    }
}
