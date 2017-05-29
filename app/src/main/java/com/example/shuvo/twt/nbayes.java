package com.example.shuvo.twt;

import android.content.Context;
import android.widget.Toast;

import java.io.*;
import java.util.*;

public class nbayes{

    public static double cprob(double x[], double[] y){
    /* x is train y is test */
        double d=1, diff;
        for(int i=0;i<x.length;i++){
            if((x[i]!=0 && y[i]!=0) )
                d *= (1000-i)*y[i]*x[i];
        }
        return d;
    }

    public static int main(double[][] d,double[] test){


        double d0=0, d1=0;

        d0 = cprob(d[0],test);
        d1 = cprob(d[1],test);

        if(d0>d1)
            return 0;
        else
            return 1;

    }
}
