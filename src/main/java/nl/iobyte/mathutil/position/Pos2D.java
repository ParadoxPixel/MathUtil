package nl.iobyte.mathutil.position;

import nl.iobyte.mathutil.matrix.Matrix;

public class Pos2D {

    private double x, y;

    public Pos2D(double[] data) {
        x = data[0];
        y = data[1];
    }

    public Pos2D(double x, double y) {
        this.x = x;
        this.y = y;
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

    public void add(double x, double y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    public Pos2D subtract(double x, double y) {
        Pos2D pos = clone();
        pos.setX(pos.getX() - x);
        pos.setY(pos.getY() - y);
        return pos;
    }

    public Matrix toMatrix() {
        double[][] line = new double[2][1];
        line[0][0] = x;
        line[1][0] = y;

        return new Matrix(line);
    }

    public Pos2D applyMatrix(Matrix r) {
        if(r == null)
            return null;

        r = r.multiply(toMatrix());
        if(r == null)
            return null;

        return new Pos2D(r.getColumn(0));
    }

    public Pos2D clone() {
        return new Pos2D(x, y);
    }

}
