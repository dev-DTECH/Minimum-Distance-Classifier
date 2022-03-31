package dev.dtech;

import java.util.ArrayList;

public class Util {
    public static String[][] normalize(String[][] arr) {


        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length - 1; j++) {

                max = Math.max(Double.parseDouble(arr[i][j]), max);
                min = Math.min(Double.parseDouble(arr[i][j]), min);


            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length-1; j++) {

                arr[i][j] = String.valueOf((Double.parseDouble(arr[i][j]) - min) / (max - min));
           }
        }
        return arr;
    }

    public static void print(String arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                System.out.print(arr[i][j] + " ");

            }
            System.out.println();

        }
    }
    public static double distance(String[] ar1,String[] ar2,int n) {
        double sum=0;
        for (int i = 0; i < n; i++){
            sum+=Math.pow(Double.parseDouble(ar1[i]) - Double.parseDouble(ar2[i]),2);
        }
        return Math.sqrt(sum);
    }
}
