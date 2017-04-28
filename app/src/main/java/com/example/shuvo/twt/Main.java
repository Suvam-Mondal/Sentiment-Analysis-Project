package com.example.shuvo.twt;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

class Main{
    static class Keys{
        static String[] keys;
        public static void getKeys(Context ctx){
            try{
                //File dir = new File("/sdcard/");
                //File fpath = new File(dir,"positivekeywords.txt");
                //BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fpath)));

                FileInputStream fis = ctx.openFileInput("positivekeywords.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line;
                line = br.readLine();
                keys = line.split(" ");
            }
            catch(Exception E){
            }
        }
    }

    public static double[] generateHist(String S[]){
        try{
            double[] hist = new double[Keys.keys.length];
            int n=0;
            for(String t:S){
                boolean val = KeyGen.isSilly(t);
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
                    hist[i]=hist[i]/n+1;
            }
            return hist;
        }
        catch(Exception e){
            return null;
        }
    }

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
				/* Input from file is dependent on format of source */
                    String[] columndet = line.split("\t");
                    columndet[0] = columndet[0].toLowerCase();
                    int cl_type = (Integer.parseInt(columndet[1]));
                    String delims = "[ -.,?!]+";
                    String[] s = columndet[0].split(delims);
                    histogram = generateHist(s);

                    for (int i = 0; i < 1000; i++) {
                        trainhist[cl_type][i] += histogram[i];
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
            else
            {
                FileInputStream fis = ctx.openFileInput("politics.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line;

                while ((line = br.readLine()) != null) {
				/* Input from file is dependent on format of source */
                    String[] columndet = line.split("\t");
                    columndet[0] = columndet[0].toLowerCase();
                    int cl_type = (Integer.parseInt(columndet[1]));
                    String delims = "[ -.,?!]+";
                    String[] s = columndet[0].split(delims);
                    histogram = generateHist(s);

                    for (int i = 0; i < 1000; i++) {
                        trainhist[cl_type][i] += histogram[i];
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
            double ham_res=0, nbayes_res=0, euc_res=0, taxicab_res=0;
            double[][] test = new double[1000][1000];
            count=0;
            int k=0;
            String oline;
            int[] exp_val = new int[max-count];
            while(( oline = bi.readLine())!=null){
                oline = oline.toLowerCase();
                String delims = "[ -.,?!]+";
                String[] s = oline.split(delims);
                test[k] = generateHist(s);
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

            String string=" ";
            string += angleFormated;
            return string;

        }
        catch(Exception E){
            String x=E.toString();
            return x;
        }
    }
}

