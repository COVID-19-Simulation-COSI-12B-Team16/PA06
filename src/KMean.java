import java.util.*;

class Sample{}
class Cluster{}

public class KMean {
    private double prevVariance;
    private List<Cluster> clusters;
    private int K;
    private double variance;
    private List<Sample> samples;

    KMean(List<Sample> _samples, int _K){
        samples = _samples;
        K = _K;
    }

    private void optimize(){}

    private void assignSamples(){}

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


    double getVariance(){ return variance;}

    List<Cluster> getResult(){ return null;}

    // write results to cvs
    void writeToFile(){}
}
