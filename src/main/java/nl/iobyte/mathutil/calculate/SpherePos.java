package nl.iobyte.mathutil.calculate;

import nl.iobyte.mathutil.cache.Rotation;
import nl.iobyte.mathutil.matrix.Matrix;

public class SpherePos {

    private final Rotation rotation;

    public SpherePos(double step, int n) {
        rotation = new Rotation(step, n);
    }

    public SpherePos(Rotation rotation) {
        this.rotation = rotation;
    }

    public Matrix getMatrix(double yaw, double pitch, double roll) {
        return getMatrix(rotation, yaw, pitch, roll);
    }

    public static Matrix getMatrix(Rotation rotation, double yaw, double pitch, double roll) {
        if(yaw == 0 && pitch == 0 && roll == 0)
            return null;

        //Calculate Sinus
        double a1, b1, c1;
        a1 = rotation.sin(yaw);
        b1 = rotation.sin(pitch);
        c1 = rotation.sin(roll);

        //Calculate Cosines
        double a2, b2, c2;
        a2 = rotation.cos(yaw);
        b2 = rotation.cos(pitch);
        c2 = rotation.cos(roll);

        //Populate
        double[][] data = new double[3][3];
        data[0][0] = a2 * b2; //M11
        data[0][1] = a2 * b1 * c1 - a1 * c2; //M12
        data[0][2] = a2 * b1 * c2 + a1 * c1; //M13

        data[1][0] = a1 * b2; //M21
        data[1][1] = a1 * b1 * c1 + a2 * c2; //M22
        data[1][2] = a1 * b1 * c2 - a2 * c1; //M23

        data[2][0] = -1 * b1; //M31
        data[2][1] = b2 * c1; //M32
        data[2][2] = b2 * c2; //M33

        return new Matrix(data);
    }

}
