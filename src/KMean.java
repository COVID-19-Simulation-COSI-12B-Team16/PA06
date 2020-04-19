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


    /**
     *
     * @return current sum of variance
     */
    private double computeVariance(){ return .0;}


    /**
     * Random pick K samples for initial iteration
     * @return list of samples
     */
    private List<Sample> randomPickSample(){ return null; }


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
