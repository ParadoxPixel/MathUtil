package nl.iobyte.mathutil.position;

import nl.iobyte.mathutil.matrix.Matrix;

import java.util.Arrays;

public class Pos3D {

    private double x, y, z;

    public Pos3D(double[] data) {
        x = data[0];
        y = data[1];
        z = data[2];
    }

    public Pos3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void add(double x, double y, double z) {
        setX(getX() + x);
        setY(getY() + y);
        setZ(getZ() + z);
    }

    public Pos3D subtract(double x, double y, double z) {
        Pos3D pos = clone();
        pos.setX(pos.getX() - x);
        pos.setY(pos.getY() - y);
        pos.setZ(pos.getZ() - z);
        return pos;
    }

    public Matrix toMatrix() {
        double[][] line = new double[3][1];
        line[0][0] = x;
        line[1][0] = y;
        line[2][0] = z;

        return new Matrix(line);
    }

    public Pos3D applyMatrix(Matrix r) {
        if(r == null)
            return null;

        r = r.multiply(toMatrix());
        if(r == null)
            return null;

        return new Pos3D(r.getColumn(0));
    }

    public Pos3D clone() {
        return new Pos3D(x, y, z);
    }

}
