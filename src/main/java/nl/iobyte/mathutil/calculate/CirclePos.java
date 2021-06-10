package nl.iobyte.mathutil.calculate;

import nl.iobyte.mathutil.cache.Rotation;
import nl.iobyte.mathutil.matrix.Matrix;

public class CirclePos {

    private final Rotation rotation;

    public CirclePos(double step, int n) {
        rotation = new Rotation(step, n);
    }

    public CirclePos(Rotation rotation) {
        this.rotation = rotation;
    }

    public Matrix getMatrix(double a) {
        return getMatrix(rotation, a);
    }

    public static Matrix getMatrix(Rotation rotation, double a) {
        if(a == 0)
            return null;

        //Rotations
        double cos = rotation.cos(a);
        double sin = rotation.sin(a);

        //Populate
        double[][] data = new double[2][2];
        data[0][0] = cos;
        data[0][1] = sin * -1;
        data[1][0] = sin;
        data[1][1] = cos;

        return new Matrix(data);
    }

}
