package nl.iobyte.mathutil.matrix;

public class Matrix {

    private final int m, n;
    private final double[][] data;

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        this.data = new double[m][n];
    }

    public Matrix(double[][] data) {
        this.m = data.length;
        this.n = data[0].length;
        this.data = data;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public double[][] getData() {
        return data;
    }

    public double getField(int m, int n) {
        if(m < 0 || m >= getM())
            return 0;

        if(n < 0 || n >= getN())
            return 0;

        return data[m][n];
    }

    public double[] getRow(int m) {
        if(m < 0 || m >= getM())
            return null;

        double[] array = new double[getN()];
        for(int i = 0; i < n; i++)
            array[i] = data[m][i];

        return array;
    }

    public double[] getColumn(int n) {
        if(n < 0 || n >= getN())
            return null;

        int j = 0;
        double[] array = new double[getM()];
        for(int i = 0; i < m; i++)
            array[j++] = data[i][n];

        return array;
    }

    public Matrix add(Matrix matrix) {
        if(matrix.getM() != m)
            return null;

        if(matrix.getN() != n)
            return null;

        double[][] newData = data.clone();
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                newData[i][j] += matrix.getField(i, j);

        return new Matrix(newData);
    }

    public Matrix subtract(Matrix matrix) {
        if(matrix.getM() != m)
            return null;

        if(matrix.getN() != n)
            return null;

        double[][] newData = data.clone();
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                newData[i][j] -= matrix.getField(i, j);

        return new Matrix(newData);
    }

    public Matrix multiply(Matrix matrix) {
        if(matrix.getM() != n)
            return null;

        double[][] newData = new double[m][matrix.getN()];
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < matrix.getN(); j++) {
                double cell = 0;
                for (int l = 0; l < n; l++)
                    cell += data[i][l] * matrix.getField(l, j);

                newData[i][j] = cell;
            }
        }

        return new Matrix(newData);
    }

    public Matrix combine(Matrix matrix) {
        if(m != matrix.getM())
            return null;

        double[][] newData = new double[m][n + matrix.getN()];
        for(int i = 0; i < m; i++) {
            System.arraycopy(data[i], 0, newData[i], 0, n);
            for (int j = 0; j < matrix.getN(); j++)
                newData[i][n + j] = matrix.getField(i, j);
        }

        return new Matrix(newData);
    }

    public Matrix clone() {
        return new Matrix(data.clone());
    }

}
