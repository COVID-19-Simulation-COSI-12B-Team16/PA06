public class KMean {
    private double prevVariance;
    private List<Cluster> clusters;
    private int K;
    private double variance;
    private List<Sample> sample;

    KMean(List<Sample> _samples, int _K){
        sample = _samples;
        K = _K;
    }

    private void optimize(){}

    private void assignSamples(){}

    // compute current sum of variance
    private double computeVariance(){ return .0;}

    private List<Sample> randomPickSample(){ return null; }


    double getVariance(){ return variance;}

    List<Cluster> getResult(){ return null;}

    // write results to cvs
    void writeToFile(){}
}
