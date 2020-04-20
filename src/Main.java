import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int NUM_OF_REPEAT = 100;
    private static final int K = 15;
    public static void main(String[] args) {
        File file = new File("result");
        if (!file.exists()) {
            file.mkdir();
        }
        run("data/s1.txt", "result/s1-cb.txt");
        run("data/s2.txt", "result/s2-cb.txt");
        run("data/s3.txt", "result/s3-cb.txt");
        run("data/s4.txt", "result/s4-cb.txt");
    }

    /**
     * Run the K-Mean on dataset for 100 times and write results to file
     * @param dataPath: dataset file path
     * @param resultPath: result file path
     */
    private static void run(String dataPath, String resultPath) {
        List<Sample> pool = readSampleFile(dataPath);
        KMean best = null;
        double curMin = Double.MAX_VALUE;
        for (int i = 0; i < NUM_OF_REPEAT; i++) {
            KMean curKm = new KMean(pool, K);
            if (curKm.getVariance() < curMin) {
                curMin = curKm.getVariance();
                best = curKm;
            }
        }
        best.writeToFile(resultPath);
    }

    /**
     * Read from a given file and parse it into a list of Samples
     * @param filename
     * @return list of Samples
     */
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
