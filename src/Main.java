import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Sample> pool = readSampleFile("data/s2.txt");
        int K = 15;
        int repeat = 1000;
        KMean km = null;
        double curMin = Double.MAX_VALUE;
        for (int i = 0; i < repeat; i++) {
            KMean curKm = new KMean(pool, K);
            if (curKm.getVariance() < curMin) {
                curMin = curKm.getVariance();
                km = curKm;
            }
        }
        return;
    }

    private static List<Sample> readSampleFile(String filename){
        List<Sample> samples = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNextDouble()) {
                double[] cor = new double[2];
                cor[0] = sc.nextDouble();
                cor[1] = sc.nextDouble();
                Sample s = new Sample(cor);
                samples.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return samples;
    }
}
