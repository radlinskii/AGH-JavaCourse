package exam;

import java.util.Random;

public class Matrix {

    private double[] data;
    private int rows;
    private int cols;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double[] getData() {
        return data;
    }

    public void setRows(int newRows) {
        rows = newRows;
    }

    public void setCols(int newCols) {
        cols = newCols;
    }

    public void setData(int[] newData) {
        data = data;
    }

    Matrix() {
        rows = 0;
        cols = 0;
    }

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    Matrix(double[][] array) {
        rows = array.length;
        int maxRowLength = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length > maxRowLength)
                maxRowLength = array[i].length;
        }
        cols = maxRowLength;
        data = new double[rows * cols];
        int arrayIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j < array[i].length) {
                    data[arrayIndex] = array[i][j];
                } else {
                    data[arrayIndex] = 0;
                }
                arrayIndex++;
            }
        }
    }

    double[][] asArray() {

        double[][] array = new double[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = data[index];
                index++;
            }
        }
        return array;
    }

    double get(int r, int c) {
        return data[r * cols + c];
    }

    void set(int r, int c, double value) {
        data[r * cols + c] = value;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        int index = 0;
        for (int i = 0; i < rows; i++) {
            buf.append("[");
            for (int j = 0; j < cols; j++) {
                buf.append(data[index] + " ");
                index++;
            }
            buf.append("]\n");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        else {
            cols = newCols;
            rows = newRows;
        }
    }

    int[] shape() {
        int[] rowsAndCols = new int[2];
        rowsAndCols[0] = rows;
        rowsAndCols[1] = cols;
        return rowsAndCols;
    }

    Matrix add(Matrix m) {
        if (rows != m.getRows() || cols != m.getCols()) {
            throw new RuntimeException("zle wymiary matrixa");
        } else {
            Matrix newMatrix = new Matrix(rows, cols);
            for (int i = 0; i < data.length; i++) {
                newMatrix.data[i] = data[i] + m.getData()[i];
            }
            return newMatrix;
        }
    }

    Matrix sub(Matrix m) {
        if (rows != m.getRows() || cols != m.getCols()) {
            throw new RuntimeException("zle wymiary matrixa");
        } else {
            Matrix newMatrix = new Matrix(rows, cols);
            for (int i = 0; i < data.length; i++) {
                newMatrix.data[i] = data[i] - m.getData()[i];
            }
            return newMatrix;
        }
    }

    Matrix mul(Matrix m) {
        if (rows != m.getRows() || cols != m.getCols()) {
            throw new RuntimeException("zle wymiary matrixa");
        } else {
            Matrix newMatrix = new Matrix(rows, cols);
            for (int i = 0; i < data.length; i++) {
                newMatrix.data[i] = data[i] * m.getData()[i];
            }
            return newMatrix;
        }
    }

    Matrix div(Matrix m) {
        if (rows != m.getRows() || cols != m.getCols()) {
            throw new RuntimeException("zle wymiary matrixa");
        } else {
            Matrix newMatrix = new Matrix(rows, cols);
            for (int i = 0; i < data.length; i++) {
                newMatrix.data[i] = data[i] / m.getData()[i];
            }
            return newMatrix;
        }
    }

    public Matrix dot(Matrix otherMatrix) {
        if (cols == otherMatrix.rows) {
            Matrix newMatrix = new Matrix(rows, otherMatrix.cols);
            double sum;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < otherMatrix.cols; j++) {
                    sum = 0;
                    for (int k = 0; k < cols; k++) {
                        sum += this.data[i * cols + k] * otherMatrix.data[k * cols + j];
                    }
                    newMatrix.data[i * newMatrix.getCols() + j] = sum;
                }
            }
            return newMatrix;
        }
        return new Matrix();
    }

    double frobenius() {
        double sum = 0;
        for (int i = 0; i < data.length; i++)
            sum += Math.pow(data[i], 2);
        return sum;
    }

    Matrix add(double w) {
        for (int i = 0; i < data.length; i++) {
            data[i] += w;
        }
        return this;
    }

    Matrix sub(double w) {
        for (int i = 0; i < data.length; i++) {
            data[i] -= w;
        }
        return this;
    }

    Matrix mul(double w) {
        for (int i = 0; i < data.length; i++) {
            data[i] *= w;
        }
        return this;
    }

    Matrix div(double w) throws DivisionByZeroException {
        if (w == 0) {
            throw new DivisionByZeroException("podzielone przez zero", "przypal");
        }
        for (int i = 0; i < data.length; i++) {
            data[i] /= w;
        }
        return this;
    }

    public static Matrix eye(int n) {
        Matrix m = new Matrix(n, n);
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.rows; j++) {
                if (i == j)
                    m.set(i, j, 1);
                else
                    m.set(i, j, 0);
            }
        }
        return m;
    }

    public static Matrix random(int rows, int cols) throws ArrayIndexOutOfBoundsException{
        Matrix m = new Matrix(rows, cols);
        Random r = new Random();
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.rows; j++) {
                m.set(i, j, r.nextDouble());
            }
        }
        return m;
    }


    //// EXAM CODE GROUP B /////


    public Matrix getTransposition(){

        Matrix result = new Matrix(this.getCols(),getRows());

        for(int i = 0; i < this.getRows(); i++){
            for (int j = 0; j < this.getCols(); j++) {
                result.set(j,i,this.get(i,j));
            }
        }
        return result;
    }

    //// EXAM CODE END /////

    public static void main(String[] args) {
        double[][] d = {{1, 2, 3, 4}, {5, 6, 7}, {6, 3, 2, 1}};
        Matrix M = new Matrix(d);
        double[][] d1 = {{1, 1, 1, 1}, {5, 6, 7}, {6, 3, 2, 1}};
        Matrix M1 = new Matrix(d1);

        Random r = new Random();
        int m = r.nextInt(100)+1;
        int n = r.nextInt(100)+1;

        Matrix M3 = Matrix.random(m,n);
        System.out.println(m + "  "+ n);
    }
}
