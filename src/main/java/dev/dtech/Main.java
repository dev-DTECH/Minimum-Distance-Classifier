package dev.dtech;

import java.io.*;
import java.util.*;

public class Main {
    public static String[][] setosa;
    public static String[][] virginica;
    public static String[][] versicolor;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        //Reading DataSet
        var DataReader = new BufferedReader(new FileReader("./iris_data.csv"));
        String line;
        var Arr = new ArrayList<String>();

        while ((line = DataReader.readLine()) != null) {
            Arr.add(line);
        }


        var data = new String[Arr.size()][Arr.get(0).split(",").length];
        for (var i = 0; i < data.length; i++) {
            for (var j = 0; j < data[0].length; j++) {
                var arr = Arr.get(i).split(",");
                data[i][j] = arr[j];
            }
        }

        //Printing Original DataSet
        Util.print(data);

        //Normalize and Print
        Util.normalize(data);
        Util.print(data);

        //Dividing into 3 arrays
        setosa = new String[50][4];
        virginica = new String[50][4];
        versicolor = new String[50][4];
        int i1 = 0, i2 = 0, i3 = 0;
        for (String[] datum : data) {

            if (datum[4].equalsIgnoreCase("Iris-setosa")) {
                setosa[i1] = datum;
                i1++;
            }
            if (datum[4].equalsIgnoreCase("Iris-virginica")) {
                virginica[i2] = datum;
                i2++;
            }


            if (datum[4].equalsIgnoreCase("Iris-versicolor")) {
                versicolor[i3] = datum;
                i3++;
            }


        }

        //Shuffling the DataSet
        List<String[]> pair = new ArrayList<String[]>(Arrays.asList(data));
        Collections.shuffle(pair);

        data = pair.toArray(new String[0][]);
        Util.print(data);


        System.out.print("Enter percentage of training sample: ");
        int train_p = sc.nextInt();
        String[][] train = new String[150 * train_p / 100][5];
        String[][] test = new String[150 * (100 - train_p) / 100][5];
        String[][] distance = new String[test.length][train.length];

        int k = 0;

        for (int i = 0; i < train.length; i++) {
            train[i] = data[k];
            k++;
        }
        for (int i = 0; i < test.length; i++) {
            test[i] = data[k];
            k++;
        }
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                distance[i][j] = String.valueOf(Util.distance(test[i],train[j],4));
            }
        }
        double acc=0;
        for (int i = 0; i < distance.length; i++) {
            double min= Double.parseDouble(distance[i][0]);
            int min_index=0;
            for (int j = 0; j < distance[0].length; j++) {
                if(min>Double.parseDouble(distance[i][j])){
                    min= Double.parseDouble(distance[i][j]);
                    min_index=j;
                }
            }

            System.out.println( "Test No " +(i+1)+" -");
            System.out.println( "Actual: "+test[i][4]);
            System.out.println( "Predicted: "+train[min_index][4]);
            System.out.println();

            if(test[i][4].equalsIgnoreCase(train[min_index][4]))
                acc++;
        }
        System.out.println("Accuracy: "+(acc/distance.length*100)+"%");
    }
}
