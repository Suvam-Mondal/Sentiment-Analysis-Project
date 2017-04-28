/*package com.example.shuvo.twt;

/**
 * Created by Shuvo on 4/13/2017.
 */
/*public class newmain {
}

*/
///////////////////////////////////
/*

class sfdfd{
    static class Keys{
        static String[] keys;
        public static void getKeys(){
            try{
                FileInputStream fis = new FileInputStream("positivekeywords.txt");
                try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
                    String line = null;
                    line = br.readLine();
                    keys = line.split(" ");
                }
            }
            catch(IOException E){
                System.out.println(E);
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
                    hist[i]=hist[i]/(n);
            }*/
			/*for(int i=0;i<hist.length;i++){
			  System.out.print(hist[i] + " ");
			  }*/
       /*     return hist;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static Double main(){
        System.out.println("inside main");
        int max=1000, count=0;
        int countp[] = {0,0};
        Keys.getKeys();
        Double ret = 0.0;
        String[] classes = {"Negetive", "Positive"};

        List<double[]> list = new ArrayList<>();
        double[] histogram = new double[1000];
        double[][] trainhist = new double[2][1000];
        try{
            FileInputStream fis = new FileInputStream("politics.txt");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
                String line = null;
                while ((line = br.readLine()) != null) {*/

                            /* Input from file is dependent on format of source */
                    /*String[] columndet = line.split("\t");
                    columndet[0] = columndet[0].toLowerCase();
                    int cl_type = (Integer.parseInt(columndet[1]));
                    String delims = "[ -.,?!]+";*/
                    //columndet[0]=columndet[0].replaceAll("not ","not_");
  /*                  String[] s = columndet[0].split(delims);
                    histogram=generateHist(s);

                    for(int i=0;i<1000;i++){
                        trainhist[cl_type][i]+=histogram[i];
                    }
                    countp[cl_type]++;


                }

                for(int i=0;i<1000;i++){
                    for(int j=0;j<2;j++){
                        //trainhist[j][i] = trainhist[j][i]/(count-1);
                        trainhist[j][i] = trainhist[j][i]/countp[j];
                    }
                }
*/
                //HistogramExample.main(trainhist[0],"Negetive Class");
                //HistogramExample.main(trainhist[1],"Positive Class");
/*
                FileInputStream fis1 = new FileInputStream("result.txt"); // tweets
                BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
                String oline = null;
                double[][] test = new double[1000][1000];
                int[] exp_val = new int[max-count];
                count=0;
                int k=0;
                while(( oline = br1.readLine())!=null){
                    oline = oline.toLowerCase();
                    //columndet[0]=columndet[0].replaceAll("not ","not_");
                    String delims = "[ -.,?!]+";
                    String[] s = oline.split(delims);
                    test[k] = generateHist(s);
                    k++;
                }


                count=0;
                Double pos;
                pos = 0.0;
                System.out.println("2.inside main" + k);
                for(int i=0;i<k;i++){

                    int idxmatch = knn.main(trainhist,test[i],"euclidian");
                    if(idxmatch==1){
                        pos++;
                        System.out.println("Positive");
                    }
                    else{
                        System.out.println("Negative");
                    }
                    count++;
                }
                ret = ((pos/count)*100);
            }


        }
        catch(IOException | NumberFormatException E){
            System.out.println(E);
        }
        return ret;
    }
}
*/