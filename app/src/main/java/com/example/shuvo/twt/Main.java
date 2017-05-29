package com.example.shuvo.twt;

import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import retrofit.RestAdapter;

class Main{
    static class Keys{
        static String[] keys;
        public static void getKeys(Context ctx){
            try{
                FileInputStream fis = ctx.openFileInput("positivekeywords.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line;
                line = br.readLine();
                keys = line.split(" ");
                br.close();

            }
            catch(Exception E){
            }
        }
    }

    public static double[] generateHist(String S[],Context ctx){
        try{
            double[] hist = new double[Keys.keys.length];
            int n=0;
            for(String t:S){
                boolean val = KeyGen.isSilly(t,ctx);
                if(!val){
                    int i=0;
                    for(String k:Keys.keys){
                        if(t.equals(k)){
                            hist[i] += 1;
                            break;
                        }
                        i++;
                    }
                }
            }
            for(int i=0;i<hist.length;i++){
                n += hist[i];
            }
            for(int i=0;i<hist.length;i++){
                if(hist[i]>0)
                    hist[i]=hist[i]/n;
            }
            return hist;
        }
        catch(Exception e){
            return null;
        }
    }

   /* public static int isNegation(String[] s,Context ctx){
        int count=0;
        try{
            FileInputStream fis = ctx.openFileInput("negetion.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            line = br.readLine();
            line = line.toLowerCase();
            String[] nwords = line.split(" ");
            for(String g:s){
                for(String n:nwords){
                    if(g.equals(n)==true){
                        count++;
                        break;
                    }
                }
            }
            br.close();
        }
        catch(Exception E){
            System.out.println(E);
        }
        return count%2;
    }*/


    public static String main(Context ctx,String choice){

        int max=1000, count=0;
        int countp[] = {0,0};
        Keys.getKeys(ctx);
        double r1=0.0,r2=0.0;
        String[] classes = {"Negetive", "Positive"};

        List<double[]> list = new ArrayList<>();
        double[] histogram = new double[1000];
        double[][] trainhist = new double[2][1000];
        try{
            if ((choice.toString()).equalsIgnoreCase("movies")) {


               FileInputStream fis = ctx.openFileInput("imdb_labelled.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] columndet = line.split("\t");
                    columndet[0] = columndet[0].toLowerCase();
                    int cl_type = (Integer.parseInt(columndet[1]));
                    String delims = "[ -.,?!]+";
                    String[] s = columndet[0].split(delims);
                   // int negindex = isNegation(s,ctx);
                    histogram = generateHist(s,ctx);
                    for(int i=0;i<1000;i++){
                            trainhist[cl_type][i]+=histogram[i];
                    }
                    countp[cl_type]++;
                    if (count++ > (90 * max / 100))
                        break;
                }
                for(int i=0;i<1000;i++){
                    for(int j=0;j<2;j++){
                        trainhist[j][i] = trainhist[j][i]/countp[j];
                       // System.out.print(trainhist[j][i]+" ");
                    }
                }
                fis.close();
            }
            else
            {
                FileInputStream fis = ctx.openFileInput("politics.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line;

                while ((line = br.readLine()) != null) {
                    String[] columndet = line.split("\t");
                    columndet[0] = columndet[0].toLowerCase();
                    int cl_type = (Integer.parseInt(columndet[1]));
                    String delims = "[ -.,?!]+";
                    String[] s = columndet[0].split(delims);
                   // int negindex = isNegation(s,ctx);
                    histogram = generateHist(s,ctx);

                    for(int i=0;i<1000;i++){
                            trainhist[cl_type][i]+=histogram[i];
                    }

                    countp[cl_type]++;
                    if (count++ > (90 * max / 100))
                        break;
                }
                for(int i=0;i<1000;i++){
                    for(int j=0;j<2;j++){
                        trainhist[j][i] = trainhist[j][i]/countp[j];
                    }
                }
                fis.close();
            }

            FileInputStream fi = ctx.openFileInput("myFile.txt");
            BufferedReader bi = new BufferedReader(new InputStreamReader(fi));
            double nbayes_res=0;
            double[][] test = new double[1000][1000];
            count=0;
            int k=0;
            int[] negindex = new int[100];
            String oline;
            while(( oline = bi.readLine())!=null){
                String[] columndet = oline.split("\t");
                columndet[0] = columndet[0].toLowerCase();
                oline = oline.toLowerCase();
                String delims = "[ -.,?!]+";
                String[] s = columndet[0].split(delims);
                //negindex[k] = isNegation(s,ctx);
                test[k] = generateHist(s,ctx);
                k++;
            }

            count=0;

            Double pos=0.0;
            for(int i=0;i<k;i++){
                int idxmatch = nbayes.main(trainhist,test[i]);
                if(idxmatch==1){
                    nbayes_res++;
                }
                count++;
            }
            r2 = ( nbayes_res/count)*100;
            float f = (float)r2;
            DecimalFormat df = new DecimalFormat("#.00");
            String angleFormated = df.format(f);

            String string="";
            string += angleFormated;
            return string;

        }
        catch(Exception E){
            String x=E.toString();
            return x;
        }
    }
}