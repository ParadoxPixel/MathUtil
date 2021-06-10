package nl.iobyte.mathutil.position;

import nl.iobyte.mathutil.matrix.Matrix;

public class Shape {

    private double x, y, z;
    private final Pos3D[] positions;

    public Shape(double x, double y, double z, Pos3D... positions) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.positions = positions;
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

    public Pos3D[] getPositions() {
        return positions;
    }

    public Pos3D[] getFinalPositions() {
        Pos3D[] array = positions.clone();
        for(Pos3D pos : array)
            pos.add(x, y, z);

        return array;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(double x, double y, double z) {
        set(getX() + x, getY() + y, getZ() + z);
    }

    public void subtract(double x, double y, double z) {
        set(getX() - x, getY() - y, getZ() - z);
    }

    public void applyMatrix(Matrix r) {
        Matrix matrix = null;
        for(Pos3D pos : positions) {
            if(matrix == null) {
                matrix = pos.toMatrix();
                continue;
            }

            matrix = matrix.combine(pos.toMatrix());
        }

        if(matrix == null)
            return;

        matrix = matrix.combine(new Pos3D(x, y, z).toMatrix());
        matrix = r.multiply(matrix);
        Pos3D pos;
        for(int i = 0; i < positions.length; i++) {
            pos = positions[i];
            pos.setX(matrix.getField(0, i));
            pos.setY(matrix.getField(1, i));
            pos.setZ(matrix.getField(2, i));
        }

        setX(matrix.getField(0, matrix.getN() - 1));
        setY(matrix.getField(1, matrix.getN() - 1));
        setZ(matrix.getField(2, matrix.getN() - 1));
    }

    public Shape clone() {
        return new Shape(x, y, z, positions.clone());
    }

}
