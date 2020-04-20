import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class KMean {
    private static final double threshold = 1E-20;
    private Double prevVariance;
    private List<Cluster> clusters;
    private int K;
    private Double variance;
    private List<Sample> samples;

    KMean(List<Sample> _samples, int _K){
        samples = _samples;
        K = _K;
        optimize();
    }

    /**
     * Iterate a number of times until variance does not change a lot
     */
    private void optimize(){
        randomPickSample();
        assignSamples();
        prevVariance = computeVariance();
        while(true){
            generateNewClusters();
            assignSamples();
            variance = computeVariance();
            if(terminationCondition()) break;
            prevVariance = variance;
        }
    }

    /**
     * Compute centers of clusters then create new empty clusters with these clusters
     */
    private void generateNewClusters(){
        List<Cluster> res = new ArrayList<>();
        for(int i = 0; i < K ; i++) {
            res.add(new Cluster(clusters.get(i).newCenter()));
        }
        clusters = res;
    }

    /**
     * Compare prevVariance and variance, if difference small enough, then return true
     * @return
     */
    private boolean terminationCondition(){
        double abs = Math.abs(prevVariance - variance);
        if(abs == 0) return true;
        return abs  / variance < KMean.threshold;
    }

    /**
     * Assign all samples to current clusters according to distances
     */
    private void assignSamples(){
        for(Sample sample : samples){
            Cluster bestCluster = clusters.get(0);
            double bestDistance = sample.distanceTo(bestCluster.getCenter());
            for(int i = 1; i < clusters.size(); i ++){
                double currentDistance = sample.distanceTo(clusters.get(i).getCenter());
                if(bestDistance > currentDistance){
                    bestDistance = currentDistance;
                    bestCluster = clusters.get(i);
                }
            }
            bestCluster.add(sample);
        }
    }


    // compute current sum of variance
    private double computeVariance(){
        double sum = 0;
        for(int i = 0; i < K; i++) {
            Cluster cur = clusters.get(i);
            sum += cur.variance();
        }
//        System.out.println("Sum of variance : " + sum);
        variance = sum;
        return sum;
    }

    private void randomPickSample(){
        Random random = new Random();
        List<Cluster> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < K; i++) {
            int index = random.nextInt(samples.size());
            while(set.contains(index)) {
                index = random.nextInt(samples.size());
            }
            set.add(index);
            Cluster c = new Cluster(samples.get(index)); // the constructor of Cluster must take a sample variable
            res.add(c);
        }
        clusters = res;
    }



    // OUTPUT METHODS BELOW

    /**
     * @return results' variance
     */
    double getVariance(){ return variance;}

    /**
     *
     * @return list of results' clusters
     */
    List<Cluster> getResult(){ return clusters;}

    /**
     * Write results (coordinate of centers) to csv
     * @param path
     */
    void writeToFile(String path){
        try {
            File file = new File(path);
            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            for (Cluster c: this.clusters) {
                Sample center = c.getCenter();
                double[] coordinate = center.getCoordinate();
                String toWrite = String.format("%d\s%d\n", (int)coordinate[0], (int)coordinate[1]);
                fw.write(toWrite);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
