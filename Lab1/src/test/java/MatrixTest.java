import org.junit.jupiter.api.Test;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    private final Matrix fourByFourMatrix = new Matrix(new double[][] {
            {1, 3, 4, 5},
            {8, 1, 9, 10},
            {2, 1, 4, 19},
            {2, 3, 1, 2}
    });
    private final Matrix fourByFourMatrix2 = new Matrix(new double[][] {
            {2, 1, 2, 8},
            {4, 1, 1, 0},
            {1, 2, 3, 19},
            {2, 4, 9, 2}
    });
    private final Matrix threeByThreeMatrix = new Matrix(new double[][] {
            {3, 2, 1},
            {3, 1, 2},
            {1, 3, 4}
    });
    private final Matrix twoByTwoMatrix = new Matrix(new double[][] {
            {2.31, 4.38},
            {1.45, 2.21}
    });
    private final Matrix oneElementMatrix = new Matrix(new double[][] {
            {1}
    });
    private final Matrix fourByFourZeroMatrix = new Matrix(new double[][] {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}});
    private final Matrix threeByThreeZeroMatrix = new Matrix(new double[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
    private final Matrix twoByTwoZeroMatrix = new Matrix(new double[][] {{0, 0}, {0, 0}});
    private final Matrix oneElementZeroMatrix = new Matrix(new double[][] {{0}});
    private final double scalarValueToMultiply = 33.33;
    private final double deviation = 0.01;
    @Test
    void constructor() {
        final double[][] toothedDoubleArray = new double[][] {
                {2, 10, 1, 5, 3, 2},
                {8, 1, 9, 10, 3},
                {2, 1, 4},
                {2, 3, 1, 2}
        };
        final double[][] nullDoubleArray = null;
        final double[][] emptyDoubleArray = new double[][] {{}, {}, {}};
        assertAll("Constructor:",
                () -> assertThrows(InvalidParameterException.class, () -> new Matrix(toothedDoubleArray)),
                () -> assertThrows(InvalidParameterException.class, () -> new Matrix(nullDoubleArray)),
                () -> assertThrows(InvalidParameterException.class, () -> new Matrix(emptyDoubleArray))
        );
    }
    @Test
    void equals() {
        assertAll("Equals (true):",
                () -> assertTrue(this.fourByFourMatrix.equals(this.fourByFourMatrix)),
                () -> assertTrue(this.threeByThreeMatrix.equals(this.threeByThreeMatrix)),
                () -> assertTrue(this.twoByTwoMatrix.equals(this.twoByTwoMatrix)),
                () -> assertTrue(this.oneElementMatrix.equals(this.oneElementMatrix))
        );
        assertAll("Equals (false):",
                () -> assertFalse(this.fourByFourMatrix.equals(this.fourByFourMatrix2)),
                () -> assertFalse(this.fourByFourMatrix.equals(this.threeByThreeMatrix)),
                () -> assertFalse(this.threeByThreeMatrix.equals(this.twoByTwoMatrix)),
                () -> assertFalse(this.twoByTwoMatrix.equals(this.oneElementMatrix))
        );
    }
    @Test
    void multiply() {
        final Matrix expectedProductFourByFourMatrix1And2 = new Matrix(new double[][] {{28, 32, 62, 94}, {49, 67, 134, 255}, {50, 87, 188, 130}, {21, 15, 28, 39}});
        final Matrix expectedProductFourByFourMatrix2And1 = new Matrix(new double[][] {{30, 33, 33, 74}, {14, 14, 29, 49}, {61, 65, 53, 120}, {56, 25, 82, 225}});
        final Matrix expectedProductFourByFourMatrixWithItself = new Matrix(new double[][] {{43, 25, 52, 121}, {54, 64, 87, 241}, {56, 68, 52, 134}, {32, 16, 41, 63}});
        final Matrix expectedProductFourByFourMatrix2WithItself = new Matrix(new double[][] {{26, 39, 83, 70}, {13, 7, 12, 51}, {51, 85, 184, 103}, {33, 32, 53, 191}});
        final Matrix expectedProductThreeByThreeMatrixWithItself = new Matrix(new double[][] {{16, 11, 11}, {14, 13, 13}, {16, 17, 23}});
        final Matrix expectedProductTwoByTwoMatrixWithItself = new Matrix(new double[][] {{11.69, 19.80}, {6.55, 11.24}});
        final Matrix expectedProductOneElementMatrixWithItself = new Matrix(new double[][] {{1}});
        assertAll("Multiply (matrix):",
                () -> assertEquals(expectedProductFourByFourMatrix1And2, this.fourByFourMatrix.multiply(this.fourByFourMatrix2)),
                () -> assertEquals(expectedProductFourByFourMatrix2And1, this.fourByFourMatrix2.multiply(this.fourByFourMatrix)),
                () -> assertEquals(expectedProductFourByFourMatrixWithItself, this.fourByFourMatrix.multiply(this.fourByFourMatrix)),
                () -> assertEquals(expectedProductFourByFourMatrix2WithItself, this.fourByFourMatrix2.multiply(this.fourByFourMatrix2)),
                () -> assertEquals(expectedProductThreeByThreeMatrixWithItself, this.threeByThreeMatrix.multiply(this.threeByThreeMatrix)),
                () -> assertEquals(expectedProductTwoByTwoMatrixWithItself, this.twoByTwoMatrix.multiply(this.twoByTwoMatrix)),
                () -> assertEquals(expectedProductOneElementMatrixWithItself, this.oneElementMatrix.multiply(this.oneElementMatrix))
        );
        final Matrix expectedProductFourByFourMatrixAndScalarValue = new Matrix(new double[][] {{33.33, 99.99, 133.32, 166.65}, {266.64, 33.33, 299.97, 333.30}, {66.66, 33.33, 133.32, 633.27}, {66.66, 99.99, 33.33, 66.66}});
        final Matrix expectedProductFourByFourMatrix2AndScalarValue = new Matrix(new double[][] {{66.66, 33.33, 66.66, 266.64}, {133.32, 33.33, 33.33, 0.0}, {33.33, 66.66, 99.99, 633.27}, {66.66, 133.32, 299.97, 66.66}});
        final Matrix expectedProductThreeByThreeMatrixAndScalarValue = new Matrix(new double[][] {{99.99, 66.66, 33.33}, {99.99, 33.33, 66.66}, {33.33, 99.99, 133.32}});
        final Matrix expectedProductTwoByTwoMatrixAndScalarValue = new Matrix(new double[][] {{76.99, 145.99}, {48.33, 73.66}});
        final Matrix expectedProductOneElementMatrixAndScalarValue = new Matrix(new double[][] {{33.33}});
        assertAll("Multiply (scalar value):",
                () -> assertEquals(expectedProductFourByFourMatrixAndScalarValue, this.fourByFourMatrix.multiply(this.scalarValueToMultiply)),
                () -> assertEquals(expectedProductFourByFourMatrix2AndScalarValue, this.fourByFourMatrix2.multiply(this.scalarValueToMultiply)),
                () -> assertEquals(expectedProductThreeByThreeMatrixAndScalarValue, this.threeByThreeMatrix.multiply(this.scalarValueToMultiply)),
                () -> assertEquals(expectedProductTwoByTwoMatrixAndScalarValue, this.twoByTwoMatrix.multiply(this.scalarValueToMultiply)),
                () -> assertEquals(expectedProductOneElementMatrixAndScalarValue, this.oneElementMatrix.multiply(this.scalarValueToMultiply))
        );
    }
    @Test
    void subtract() {
        assertAll("Subtract (throws):",
                () -> assertThrows(IllegalArgumentException.class, () -> this.fourByFourMatrix.subtract(this.threeByThreeMatrix)),
                () -> assertThrows(IllegalArgumentException.class, () -> this.threeByThreeMatrix.subtract(this.twoByTwoMatrix)),
                () -> assertThrows(IllegalArgumentException.class, () -> this.twoByTwoMatrix.subtract(this.oneElementMatrix))
        );
        final Matrix expectedDifferenceFourByFourMatrix1And2 = new Matrix(new double[][] {{-1, 2, 2, -3}, {4, 0, 8, 10}, {1, -1, 1, 0}, {0, -1, -8, 0}});
        assertAll("Subtract (equals):",
                () -> assertEquals(expectedDifferenceFourByFourMatrix1And2, this.fourByFourMatrix.subtract(this.fourByFourMatrix2)),
                () -> assertEquals(this.fourByFourZeroMatrix, this.fourByFourMatrix.subtract(this.fourByFourMatrix)),
                () -> assertEquals(this.fourByFourZeroMatrix, this.fourByFourMatrix2.subtract(this.fourByFourMatrix2)),
                () -> assertEquals(this.threeByThreeZeroMatrix, this.threeByThreeMatrix.subtract(this.threeByThreeMatrix)),
                () -> assertEquals(this.twoByTwoZeroMatrix, this.twoByTwoMatrix.subtract(this.twoByTwoMatrix)),
                () -> assertEquals(this.oneElementZeroMatrix, this.oneElementMatrix.subtract(this.oneElementMatrix))
        );
    }
    @Test
    void determinant() {
        final double expectedFourByFourMatrixDeterminant = -1334.00;
        final double expectedFourByFourMatrix2Determinant = -234.00;
        final double expectedThreeByThreeMatrixDeterminant = -18.00;
        final double expectedTwoByTwoMatrixDeterminant = -1.25;
        final double expectedOneElementMatrixDeterminant = 1.00;
        assertAll("Determinant (expected):",
                () -> assertTrue((expectedFourByFourMatrixDeterminant - this.fourByFourMatrix.determinant()) <= this.deviation),
                () -> assertTrue((expectedFourByFourMatrix2Determinant - this.fourByFourMatrix2.determinant()) <= this.deviation),
                () -> assertTrue((expectedThreeByThreeMatrixDeterminant - this.threeByThreeMatrix.determinant()) <= this.deviation),
                () -> assertTrue((expectedTwoByTwoMatrixDeterminant - this.twoByTwoMatrix.determinant()) <= this.deviation),
                () -> assertTrue((expectedOneElementMatrixDeterminant - this.oneElementMatrix.determinant()) <= this.deviation)
        );
        final double wrongFourByFourMatrixDeterminant = -999.99;
        final double wrongFourByFourMatrix2Determinant = -88.88;
        final double wrongThreeByThreeMatrixDeterminant = -9.99;
        final double wrongTwoByTwoMatrixDeterminant = -0.99;
        final double wrongOneElementMatrixDeterminant = 1.99;
        assertAll("Determinant (wrong):",
                () -> assertFalse((wrongFourByFourMatrixDeterminant - this.fourByFourMatrix.determinant()) <= this.deviation),
                () -> assertFalse((wrongFourByFourMatrix2Determinant - this.fourByFourMatrix2.determinant()) <= this.deviation),
                () -> assertFalse((wrongThreeByThreeMatrixDeterminant - this.threeByThreeMatrix.determinant()) <= this.deviation),
                () -> assertFalse((wrongTwoByTwoMatrixDeterminant - this.twoByTwoMatrix.determinant()) <= this.deviation),
                () -> assertFalse((wrongOneElementMatrixDeterminant - this.oneElementMatrix.determinant()) <= this.deviation)
        );
    }
    @Test
    void invert() {
        final Matrix expectedFourByFourMatrixInverted = new Matrix(new double[][] {{-0.28, 0.10, -0.00, 0.25}, {0.12, -0.07, -0.02, 0.25}, {0.27, 0.05, -0.07, -0.27}, {-0.03, -0.02, 0.07, 0.02}});
        final Matrix expectedFourByFourMatrix2Inverted = new Matrix(new double[][] {{0.40, 0.12, -0.16, -0.05}, {-2.67, 1.00, 1.11, 0.11}, {1.08, -0.46, -0.46, 0.08}, {0.09, -0.04, 0.02, -0.02}});
        final Matrix expectedThreeByThreeMatrixInverted = new Matrix(new double[][] {{0.11, 0.28, -0.17}, {0.56, -0.61, 0.17}, {-0.44, 0.39, 0.17}});
        final Matrix expectedTwoByTwoMatrixInverted = new Matrix(new double[][] {{-1.77, 3.52}, {1.16, -1.85}});
        assertAll("Invert (true):",
                () -> assertTrue(expectedFourByFourMatrixInverted.equals(this.fourByFourMatrix.invert())),
                () -> assertTrue(expectedFourByFourMatrix2Inverted.equals(this.fourByFourMatrix2.invert())),
                () -> assertTrue(expectedThreeByThreeMatrixInverted.equals(this.threeByThreeMatrix.invert())),
                () -> assertTrue(expectedTwoByTwoMatrixInverted.equals(this.twoByTwoMatrix.invert()))
        );
        assertAll("Invert (false):",
                () -> assertFalse(this.fourByFourZeroMatrix.equals(this.fourByFourMatrix.invert())),
                () -> assertFalse(this.fourByFourZeroMatrix.equals(this.fourByFourMatrix2.invert())),
                () -> assertFalse(this.threeByThreeZeroMatrix.equals(this.threeByThreeMatrix.invert())),
                () -> assertFalse(this.twoByTwoZeroMatrix.equals(this.twoByTwoMatrix.invert()))
        );
    }
    @Test
    void transpose() {
        final Matrix expectedFourByFourMatrixTransposed = new Matrix(new double[][] {{1, 8, 2, 2}, {3, 1, 1, 3}, {4, 9, 4, 1}, {5, 10, 19, 2}});
        final Matrix expectedFourByFourMatrix2Transposed = new Matrix(new double[][] {{2, 4, 1, 2}, {1, 1, 2, 4}, {2, 1, 3, 9}, {8, 0, 19, 2}});
        final Matrix expectedThreeByThreeMatrixTransposed = new Matrix(new double[][] {{3, 3, 1}, {2, 1, 3}, {1, 2, 4}});
        final Matrix expectedTwoByTwoMatrixTransposed = new Matrix(new double[][] {{2.31, 1.45}, {4.38, 2.21}});
        assertAll("Transpose (equals):",
                () -> assertEquals(expectedFourByFourMatrixTransposed, this.fourByFourMatrix.transpose()),
                () -> assertEquals(expectedFourByFourMatrix2Transposed, this.fourByFourMatrix2.transpose()),
                () -> assertEquals(expectedThreeByThreeMatrixTransposed, this.threeByThreeMatrix.transpose()),
                () -> assertEquals(expectedTwoByTwoMatrixTransposed, this.twoByTwoMatrix.transpose())
        );
        assertAll("Transpose (not equals):",
                () -> assertNotEquals(this.fourByFourZeroMatrix, this.fourByFourMatrix.transpose()),
                () -> assertNotEquals(this.fourByFourZeroMatrix, this.fourByFourMatrix2.transpose()),
                () -> assertNotEquals(this.threeByThreeZeroMatrix, this.threeByThreeMatrix.transpose()),
                () -> assertNotEquals(this.twoByTwoZeroMatrix, this.twoByTwoMatrix.transpose())
        );
    }
    @Test
    void solution() {
        final Matrix solutionResultMatrix = this.fourByFourMatrix.invert().subtract(this.fourByFourMatrix2.transpose().multiply(this.scalarValueToMultiply));
        final Matrix expectedSolutionResultMatrix = new Matrix(new double[][] {{-66.94, -133.22, -33.33, -66.41}, {-33.21, -33.40, -66.68, -133.07}, {-66.39, -33.28, -100.06, -300.24}, {-266.67, -0.02, -633.20, -66.64}});
        assertAll("Solution for task:",
                () -> assertTrue(solutionResultMatrix.equals(expectedSolutionResultMatrix)),
                () -> assertEquals(expectedSolutionResultMatrix, solutionResultMatrix)
        );
    }
}