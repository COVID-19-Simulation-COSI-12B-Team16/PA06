import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
public class Cluster {
    private final Sample center;
    private final Set<Sample> includedSamples;
    
    public Cluster(Sample c) {
        center = c;
        includedSamples = new HashSet<>();
    }

    public void add(Sample s) {
        if (s == null || s.dimension() != center.dimension()) {
            try {
                throw new Exception("Invalid incoming sample, null or dimension mismatch");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        includedSamples.add(s);
    }

    public double variance() {
        Iterator<Sample> it = includedSamples.iterator();
        double result = 0;
        while (it.hasNext()) {
            Sample s = it.next();
            result += s.distanceTo(center);
        }
        return result;
    }

    public Sample newCenter() {
        Iterator<Sample> it = includedSamples.iterator();
        double[] coordinateSum = new double[center.dimension()];
        while (it.hasNext()) {
            Sample s = it.next();
            double[] coordinate = s.getCoordinate();
            for (int i = 0; i < coordinateSum.length; i++) {
                coordinateSum[i] += coordinate[i];
            }
        }
        for (int i = 0; i < coordinateSum.length; i++) {
            coordinateSum[i] /= includedSamples.size();
        }
        return new Sample(coordinateSum);
    }
}