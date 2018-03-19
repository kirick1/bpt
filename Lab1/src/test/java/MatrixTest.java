import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    private final Matrix fourByFour = new Matrix(new double[][]{
            {1, 3, 4, 5},
            {8, 1, 9, 10},
            {2, 1, 4, 19},
            {2, 3, 1, 2}
    });
    private final Matrix fourByFour2 = new Matrix(new double[][]{
            {2, 1, 2, 8},
            {4, 1, 1, 0},
            {1, 2, 3, 19},
            {2, 4, 9, 2}
    });
    private final Matrix threeByThree = new Matrix(new double[][]{
            {3, 2, 1},
            {3, 1, 2},
            {1, 3, 4}
    });
    private final Matrix twoByTwo = new Matrix(new double[][]{
            {2.31, 4.38},
            {1.45, 2.21}
    });
    private final Matrix oneElementMatrix = new Matrix(new double[][]{
            {1}
    });

    @Test
    void constructor() {
        double[][] toothed = {
                {2, 10, 1, 5, 3, 2},
                {8, 1, 9, 10, 3},
                {2, 1, 4},
                {2, 3, 1, 2}
        };
        assertAll("Constructor",
                () -> assertThrows(InvalidParameterException.class, () -> new Matrix(toothed)),
                () -> assertThrows(InvalidParameterException.class, () -> new Matrix(null)),
                () -> assertThrows(InvalidParameterException.class, () -> new Matrix(new double[][]{{}, {}, {}}))
        );
    }
    @Test
    void equals() {
        assertAll("Equals",
                () -> assertTrue(fourByFour.equals(fourByFour)),
                () -> assertTrue(!fourByFour.equals(fourByFour2)),
                () -> assertTrue(!fourByFour.equals(threeByThree)),
                () -> assertTrue(twoByTwo.equals(twoByTwo))
        );
    }
    @Test
    void diff() {
        Matrix expectedDiff = new Matrix(new double[][]{
                {-1, 2, 2, -3},
                {4, 0, 8, 10},
                {1, -1, 1, 0},
                {0, -1, -8, 0}
        });
        Matrix expectedDiff2 = new Matrix(new double[][]{
                {0, 0},
                {0, 0},

        });
        Matrix expectedDiff3 = new Matrix(new double[][]{{0}});
        assertAll("Diff",
                () -> assertEquals(expectedDiff, fourByFour.subtract(fourByFour2)),
                () -> assertThrows(IllegalArgumentException.class, () -> fourByFour.subtract(threeByThree)),
                () -> assertEquals(expectedDiff2, twoByTwo.subtract(twoByTwo)),
                () -> assertEquals(expectedDiff3, oneElementMatrix.subtract(oneElementMatrix))
        );
    }
    /*@Test
    void determinant() {
        Matrix matrix = new Matrix();
        double[][] array1 = new double[][]{
                {4, 5},
                {4, 5}
        };
        Matrix mt1 = new Matrix(array1);
        double[][] array2 = new double[][]{
                {3, 5},
                {2, 4}
        };
        Matrix mt2 = new Matrix(array2);
        double[][] array3 = new double[][]{
                {1}
        };
        Matrix mt3 = new Matrix(array3);
        assertAll("determinant",
                () -> assertThrows(IllegalStateException.class, matrix::determinant),
                () -> assertThrows(IllegalArgumentException.class, matrix::determinant),
                () -> assertEquals(0, mt1.determinant()),
                () -> assertEquals(2, mt2.determinant()),
                () -> assertEquals(1, mt3.determinant()),
                () -> assertEquals(-18, threeByThree.determinant()));
    }*/
    @Test
    void multiply() {
        Matrix expectedMultiplication = new Matrix(new double[][]{
                {28, 32, 62, 94},
                {49, 67, 134, 255},
                {50, 87, 188, 130},
                {21, 15, 28, 39}
        });
        assertAll("Multiplication",
                () -> assertEquals(expectedMultiplication, fourByFour.multiply(fourByFour2)),
                () -> assertThrows(IllegalArgumentException.class, () -> fourByFour.multiply(threeByThree)),
                () -> assertEquals(oneElementMatrix, oneElementMatrix.multiply(oneElementMatrix))
        );
    }

    @Test
    void inverse() {
        Matrix invalidInverse = new Matrix(new double[][]{
                {4, 5},
                {4, 5}
        });
        Matrix inversedTwoByTwo = new Matrix(new double[][]{
                {4, 6},
                {4, 7}
        });
        Matrix expectedTwoByTwo = new Matrix(new double[][]{
                {1.75, -1.5},
                {-1, 1}
        });
        Matrix expectedThreeByThree = new Matrix(new double[][]{
                {0.11, 0.27, -0.16},
                {0.55, -0.61, 0.16},
                {-0.44, 0.38, 0.16}
        });
        assertAll("Inverse",
                () -> assertEquals(expectedThreeByThree, threeByThree.inverse()),
                () -> assertEquals(expectedTwoByTwo, inversedTwoByTwo.inverse()),
                () -> assertThrows(ArithmeticException.class, () -> invalidInverse.inverse())
        );
    }
    @Test
    void solution() {
        final double value = 12.34;
        Matrix matrixA = new Matrix(new double[][]{
                {3, 2, 1},
                {3, 1, 2},
                {1, 3, 4}
        });
        Matrix matrixB = new Matrix(new double[][]{
                {3, 2, 3},
                {4, 1, 2},
                {1, 5, 4}
        });
        matrixA = matrixA.inverse();
        matrixB = matrixB.transpose();
        matrixB = matrixB.multiply(value);
        Matrix result = matrixA.subtract(matrixB);
        Matrix expected = new Matrix(new double[][]{
                {-36.91, -49.08, -12.50},
                {-24.12, -12.95, -61.53},
                {-37.46, -24.29, -49.19}
        });
        assertAll("Equals",
                () -> assertTrue(result.equals(expected))
        );
    }
}