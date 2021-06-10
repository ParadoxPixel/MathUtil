package nl.iobyte.mathutil.cache;

import java.util.HashMap;
import java.util.Map;

public class Rotation {

    private final double step;
    private final int n;

    private final Map<Double, Double> sin = new HashMap<>();
    private final Map<Double, Double> cos = new HashMap<>();

    public Rotation(double step, int n) {
        this.step = Math.abs(step);
        this.n = (int) Math.pow(10, n);

        double radian;
        for(double i = 0; i < 360; i += step) {
            radian = Math.toRadians(i);
            this.sin.put(i, round(Math.sin(radian)));
            this.cos.put(i, round(Math.cos(radian)));
        }
    }

    /**
     * Get step size
     * @return double
     */
    public double getStep() {
        return step;
    }

    /**
     * Get map of cached sinus values
     * @return Map
     */
    public Map<Double, Double> getSin() {
        return sin;
    }

    /**
     * Get map of cached cosines values
     * @return Map
     */
    public Map<Double, Double> getCos() {
        return cos;
    }

    /**
     * Get sinus of angle
     * @param a angle in degrees
     * @return sinus
     */
    public double sin(double a) {
        double angle = getAngle(a);
        return sin.get(angle);
    }

    /**
     * Get cosine of angle
     * @param a angle in degrees
     * @return cosine
     */
    public double cos(double a) {
        double angle = getAngle(a);
        return cos.get(angle);
    }

    /**
     * Get tangent of angle
     * @param a angle in degrees
     * @return tangent
     */
    public double tan(double a) {
        double cos = cos(a);
        return cos == 0 ? 0 : sin(a) / cos;
    }

    /**
     * Get's angle closest to step
     * @param a Angle
     * @return Closest angle to step
     */
    public double getAngle(double a) {
        //Get absolute value
        a = a % 360;
        if(a < 0)
            a = 360 + a;

        //Determine the numbers on either side of value
        double low = a - a % step;
        double high = low + step;

        // Return the closest one
        return a - low < high - a ? low : high;
    }

    /**
     * Round double to precision of n
     * @param a double
     * @return double with precision of n
     */
    public double round(double a) {
        return (double) ((int) (a * n)) / n;
    }

}
