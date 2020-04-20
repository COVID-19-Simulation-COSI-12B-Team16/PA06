import java.util.Arrays;

public class Sample {
    private final double[] coordinate;
    private final int dimension;
    
    public Sample(double[] input) {
        coordinate = input.clone(); // Prevent possible outside modification
        dimension = input.length;
    }

    public double distanceTo(Sample s) {
        if (s == null || this.dimension != s.dimension) {
            try {
                throw new Exception("Dimension mismatch");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        double squreSum = 0;
        for (int i = 0; i < coordinate.length; i++) {
            double diff = Math.abs(s.coordinate[i] - this.coordinate[i]);
            squreSum += Math.pow(diff, 2);
        }
        return Math.sqrt(squreSum);
    }

    public int dimension() {
        return dimension;
    }

    public double[] getCoordinate() {
        return coordinate.clone(); // Prevent possible outside modification
    }
    
    @Override
    public String toString() {
        return Arrays.toString(coordinate);
    }
}