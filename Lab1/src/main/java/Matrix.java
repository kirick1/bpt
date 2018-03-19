import java.security.InvalidParameterException;

public class Matrix {
    private double[][] matrix;
    Matrix(double[][] array) {
        if (!isValid(array)) throw new InvalidParameterException("Passed array is not valid matrix");
        else this.matrix = array;
    }
    Matrix() {}
    private boolean isValid(double[][] array) {
        if (array == null) return false;
        int rowsNumber = array.length;
        if (rowsNumber == 0) return false;
        for (double[] row : array) if (row.length != rowsNumber) return false;
        return true;
    }
    private double[][] getMatrix() {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else return this.matrix;
    }
    private int getSize() {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else return this.matrix.length;
    }
    @Override
    public String toString() {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else {
            StringBuilder matrixString = new StringBuilder();
            int matrixSize = this.getSize();
            for (int i = 0; i < matrixSize; i++) {
                matrixString.append("[");
                for (int j = 0; j < matrixSize; j++) matrixString.append(String.format("%.2f ", this.matrix[i][j]));
                matrixString.append("]\n");
            }
            return matrixString.toString();
        }
    }
    @Override
    public boolean equals(Object object) {
        if(object instanceof Matrix) {
            Matrix mt = (Matrix) object;
            if (!isValid(this.matrix) || !isValid(mt.getMatrix())) return false;
            else {
                int matrixSize = this.getSize(), objectMatrixSize = mt.getSize();
                if (matrixSize != objectMatrixSize) return false;
                else {
                    final double deviation = 0.01;
                    for (int i = 0; i < matrixSize; i++)
                        for (int j = 0; j < matrixSize; j++)
                            if ((this.matrix[i][j] - mt.getMatrix()[i][j]) > deviation) return false;
                    return true;
                }
            }
        } else return false;
    }
    public Matrix multiply(Matrix multiplier) {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else if (!isValid(multiplier.getMatrix())) throw new IllegalArgumentException("Multiplier matrix is not valid");
        else {
            int matrixSize = this.getSize(), multiplierMatrixSize = multiplier.getSize();
            if (matrixSize != multiplierMatrixSize) throw new InvalidParameterException("Size of current matrix is not equal with size of multiplier matrix");
            else {
                double[][] resultMatrix = new double[matrixSize][multiplierMatrixSize];
                for (int i = 0; i < matrixSize; i++)
                    for (int j = 0; j < multiplierMatrixSize; j++)
                        for (int k = 0; k < matrixSize; k++)
                            resultMatrix[i][j] += this.matrix[i][k] * multiplier.getMatrix()[k][j];
                return new Matrix(resultMatrix);
            }
        }
    }
    public Matrix multiply(double value) {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else {
            int matrixSize = this.getSize();
            double[][] resultMatrix = new double[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++)
                for (int j = 0; j < matrixSize; j++)
                    resultMatrix[i][j] = this.matrix[i][j] * value;
            return new Matrix(resultMatrix);
        }
    }
    public Matrix subtract(Matrix subtrahend) {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else if (!isValid(subtrahend.getMatrix())) throw new IllegalArgumentException("Subtrahend matrix is not valid");
        else {
            int matrixSize = this.getSize(), subtrahendMatrixSize = subtrahend.getSize();
            if (matrixSize != subtrahendMatrixSize) throw new InvalidParameterException("Current matrix and subtrahend matrix must have equal size");
            else {
                double[][] resultMatrix = new double[subtrahendMatrixSize][subtrahendMatrixSize];
                for (int i = 0; i < matrixSize; i++)
                    for (int j = 0; j < matrixSize; j++)
                        resultMatrix[i][j] = this.matrix[i][j] - subtrahend.getMatrix()[i][j];
                return new Matrix(resultMatrix);
            }
        }
    }
    private Matrix minor(int row, int column) {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else {
            int matrixSize = this.getSize();
            double[][] minorMatrix = new double[matrixSize - 1][matrixSize - 1];
            for (int i = 0; i < matrixSize; i++)
                for (int j = 0; (i != row) && (j < matrixSize); j++)
                    if (j != column) {
                        int currentMinorRow = (i < row) ? i : (i - 1), currentMinorColumn = (j < column) ? j : (j - 1);
                        minorMatrix[currentMinorRow][currentMinorColumn] = this.matrix[i][j];
                    }
            return new Matrix(minorMatrix);
        }
    }
    public double determinant() {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else {
            int matrixSize = this.getSize();
            if (matrixSize == 0) throw new IllegalArgumentException("Current matrix is empty");
            else if (matrixSize == 1) return this.matrix[0][0];
            else if (matrixSize == 2) return this.matrix[0][0] * this.matrix[1][1] - this.matrix[0][1] * this.matrix[1][0];
            else {
                double determinantValue = 0.0;
                for (int i = 0; i < matrixSize; i++) determinantValue += Math.pow(-1, i) * this.matrix[0][i] * minor(0, i).determinant();
                return determinantValue;
            }
        }
    }
    public Matrix inverse() {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else {
            int matrixSize = this.getSize();
            double[][] inversed = new double[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++)
                for (int j = 0; j < matrixSize; j++)
                    inversed[i][j] = Math.pow(-1, i + j) * minor(i, j).determinant();
            double determinant = this.determinant();
            if (determinant == 0) throw new ArithmeticException("Division by zero: determinant of current matrix is 0");
            else {
                for (int i = 0; i < matrixSize; i++)
                    for (int j = 0; j <= i; j++) {
                        double tmp = inversed[i][j];
                        inversed[i][j] = inversed[j][i] / determinant;
                        inversed[j][i] = tmp / determinant;
                    }
                return new Matrix(inversed);
            }
        }
    }
    public Matrix transpose() {
        if (!isValid(this.matrix)) throw new IllegalArgumentException("Current matrix is not valid");
        else {
            int matrixSize = this.getSize();
            double[][] transposed = this.matrix;
            for (int i = 0; i < matrixSize; i++)
                for (int j = i + 1; j < matrixSize; j++) {
                    double tmp = transposed[i][j];
                    transposed[i][j] = transposed[j][i];
                    transposed[j][i] = tmp;
                }
            return new Matrix(transposed);
        }
    }
}