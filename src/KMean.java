import java.util.*;

public class KMean {
    private Double prevVariance;
    private List<Cluster> clusters;
    private int K;
    private Double variance;
    private List<Sample> samples;

    KMean(List<Sample> _samples, int _K){
        samples = _samples;
        K = _K;
    }

    /**
     * Iterate a number of times until variance does not change a lot
     */
    private void optimize(){
        randomPickSample();
        assignSamples();
        prevVariance = computeVariance();
        while(variance == None){
            generateNewClusters();
            assignSamples();
            variance = computeVariance();
            if(terminationCondition()) break;
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
    private boolean terminationCondition(){ return false;}

    /**
     * Assign all samples to current clusters according to distances
     */
    private void assignSamples(){
        for(Sample sample : samples){
            if(sample.isAssigned) continue;
            Cluster bestCluster = clusters.get(0);
            double bestDistance = Sample.getDistance(sample, bestCluster.center);
            for(int i = 1; i < clusters.length(); i ++){
                double currentDistance = Sample.getDistance(sample, clusters.get(i));
                if(bestDistance > currentDistance){
                    bestDistance = currentDistance;
                    bestCluster = clusters.get(i);
                }
            }
            bestCluster.add(sample);
            sample.setAssigned(true);
        }
    }


    // compute current sum of variance
    private double computeVariance(){
        double sum = 0;
        for(int i = 0; i < K; i++) {
            Cluster cur = clusters.get(i);
            sum += cur.variance();
        }
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
    List<Cluster> getResult(){ return null;}

    /**
     * Write results to csv
     * @param path
     */
    void writeToFile(String path){}
}
