import java.util.*;

class Sample{}
class Cluster{}

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
            Cluster bestCluster = clusters.get(0);
            double bestDistance = sample.distanceTo(bestCluster.center);
            for(int i = 1; i < clusters.length(); i ++){
                double currentDistance = sample.distanceTo(clusters.get(i));
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
            for(int j = 0; j < cur.getSamples().size(); j++) {
                sum += getDistance(cur.getCenter(), cur.getSamples().get(j));
            }
        }

        return sum;
    }

    private void randomPickSample(){
        Random random = new Random();
        List<Sample> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < K; i++) {
            int index = random.nextInt(samples.size());
            while(set.contains(index)) {
                index = random.nextInt(samples.size());
            }
            set.add(index);
            Cluster c = new Cluster(samples.get(index)); // the constructor of Cluster must take a sample variable
            clusters.add(c);
        }
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
     * Write results to csv
     * @param path
     */
    void writeToFile(String path){

    }
}
