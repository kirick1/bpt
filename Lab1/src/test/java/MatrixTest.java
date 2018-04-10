import org.junit.jupiter.api.Test;
import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    private final double[][] toothedDoubleArray = new double[][] {
            {2, 10, 1, 5, 3, 2},
            {8, 1, 9, 10, 3},
            {2, 1, 4},
            {2, 3, 1, 2}
    };
    private final double[][] emptyDoubleArray = new double[][] {
            {},
            {},
            {}
    };
    private final double[][] fourByFourDoubleArray = new double[][] {
            {1, 3, 4, 5},
            {8, 1, 9, 10},
            {2, 1, 4, 19},
            {2, 3, 1, 2}
    };
    private final double[][] fourByFourDoubleArray2 = new double[][] {
            {2, 1, 2, 8},
            {4, 1, 1, 0},
            {1, 2, 3, 19},
            {2, 4, 9, 2}
    };
    private final double[][] threeByThreeDoubleArray = new double[][] {
            {3, 2, 1},
            {3, 1, 2},
            {1, 3, 4}
    };
    private final double[][] twoByTwoDoubleArray = new double[][] {
            {2.31, 4.38},
            {1.45, 2.21}
    };
    private final double[][] oneElementDoubleArray = new double[][] {
            {1}
    };
    private final double[][] fourByFourZeroDoubleArray = new double[][] {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    private final double[][] threeByThreeZeroDoubleArray = new double[][] {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    private final double[][] twoByTwoZeroDoubleArray = new double[][] {
            {0, 0},
            {0, 0}
    };
    private final double[][] oneElementZeroDoubleArray = new double[][] {
            {0}
    };
    @Test
    void constructor() {
        assertAll("Constructor:",
                () -> assertThrows(InvalidParameterException.class, () -> new Matrix(this.toothedDoubleArray)),
                () -> assertThrows(InvalidParameterException.class, () -> new Matrix(null)),
                () -> assertThrows(InvalidParameterException.class, () -> new Matrix(this.emptyDoubleArray))
        );
    }
    private final Matrix fourByFourMatrix = new Matrix(this.fourByFourDoubleArray);
    private final Matrix fourByFourMatrix2 = new Matrix(this.fourByFourDoubleArray2);
    private final Matrix threeByThreeMatrix = new Matrix(this.threeByThreeDoubleArray);
    private final Matrix twoByTwoMatrix = new Matrix(this.twoByTwoDoubleArray);
    private final Matrix oneElementMatrix = new Matrix(this.oneElementDoubleArray);
    @Test
    void getMatrix() {
        assertAll("Get matrix:",
                () -> assertArrayEquals(this.fourByFourDoubleArray, this.fourByFourMatrix.getMatrix()),
                () -> assertArrayEquals(this.fourByFourDoubleArray2, this.fourByFourMatrix2.getMatrix()),
                () -> assertArrayEquals(this.threeByThreeDoubleArray, this.threeByThreeMatrix.getMatrix()),
                () -> assertArrayEquals(this.twoByTwoDoubleArray, this.twoByTwoMatrix.getMatrix()),
                () -> assertArrayEquals(this.oneElementDoubleArray, this.oneElementMatrix.getMatrix())
        );
    }
    @Test
    void getSize() {
        assertAll("Get size:",
                () -> assertEquals(4, this.fourByFourMatrix.getSize()),
                () -> assertEquals(4, this.fourByFourMatrix2.getSize()),
                () -> assertEquals(3, this.threeByThreeMatrix.getSize()),
                () -> assertEquals(2, this.twoByTwoMatrix.getSize()),
                () -> assertEquals(1, this.oneElementMatrix.getSize())
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
    private final Matrix expectedProductFourByFourMatrix1And2 = new Matrix(new double[][] {
            {28, 32, 62, 94},
            {49, 67, 134, 255},
            {50, 87, 188, 130},
            {21, 15, 28, 39}
    });
    private final Matrix expectedProductFourByFourMatrix2And1 = new Matrix(new double[][] {
            {30, 33, 33, 74},
            {14, 14, 29, 49},
            {61, 65, 53, 120},
            {56, 25, 82, 225}
    });
    private final Matrix expectedProductFourByFourMatrix = new Matrix(new double[][] {
            {43, 25, 52, 121},
            {54, 64, 87, 241},
            {56, 68, 52, 134},
            {32, 16, 41, 63}
    });
    private final Matrix expectedProductFourByFourMatrix2 = new Matrix(new double[][] {
            {26, 39, 83, 70},
            {13, 7, 12, 51},
            {51, 85, 184, 103},
            {33, 32, 53, 191}
    });
    private final Matrix expectedProductThreeByThreeMatrix = new Matrix(new double[][] {
            {16, 11, 11},
            {14, 13, 13},
            {16, 17, 23}
    });
    private final Matrix expectedProductTwoByTwoMatrix = new Matrix(new double[][] {
            {11.69, 19.80},
            {6.55, 11.24}
    });
    private final Matrix expectedProductOneElementMatrix = new Matrix(new double[][] {
            {1}
    });
    private final double scalarValueToMultiply = 33.33;
    private final Matrix expectedProductFourByFourMatrixAndScalarValue = new Matrix(new double[][] {
            {33.33, 99.99, 133.32, 166.65},
            {266.64, 33.33, 299.97, 333.30},
            {66.66, 33.33, 133.32, 633.27},
            {66.66, 99.99, 33.33, 66.66}
    });
    private final Matrix expectedProductFourByFourMatrix2AndScalarValue = new Matrix(new double[][] {
            {66.66, 33.33, 66.66, 266.64},
            {133.32, 33.33, 33.33, 0.0},
            {33.33, 66.66, 99.99, 633.27},
            {66.66, 133.32, 299.97, 66.66}
    });
    private final Matrix expectedProductThreeByThreeMatrixAndScalarValue = new Matrix(new double[][] {
            {99.99, 66.66, 33.33},
            {99.99, 33.33, 66.66},
            {33.33, 99.99, 133.32}
    });
    private final Matrix expectedProductTwoByTwoMatrixAndScalarValue = new Matrix(new double[][] {
            {76.99, 145.99},
            {48.33, 73.66}
    });
    private final Matrix expectedProductOneElementMatrixAndScalarValue = new Matrix(new double[][] {
            {33.33}
    });
    @Test
    void multiply() {
        assertAll("Multiply (matrix):",
                () -> assertEquals(this.expectedProductFourByFourMatrix1And2, this.fourByFourMatrix.multiply(this.fourByFourMatrix2)),
                () -> assertEquals(this.expectedProductFourByFourMatrix2And1, this.fourByFourMatrix2.multiply(this.fourByFourMatrix)),
                () -> assertEquals(this.expectedProductFourByFourMatrix, this.fourByFourMatrix.multiply(this.fourByFourMatrix)),
                () -> assertEquals(this.expectedProductFourByFourMatrix2, this.fourByFourMatrix2.multiply(this.fourByFourMatrix2)),
                () -> assertEquals(this.expectedProductThreeByThreeMatrix, this.threeByThreeMatrix.multiply(this.threeByThreeMatrix)),
                () -> assertEquals(this.expectedProductTwoByTwoMatrix, this.twoByTwoMatrix.multiply(this.twoByTwoMatrix)),
                () -> assertEquals(this.expectedProductOneElementMatrix, this.oneElementMatrix.multiply(this.oneElementMatrix))
        );
        assertAll("Multiply (scalar value):",
                () -> assertEquals(this.expectedProductFourByFourMatrixAndScalarValue, this.fourByFourMatrix.multiply(this.scalarValueToMultiply)),
                () -> assertEquals(this.expectedProductFourByFourMatrix2AndScalarValue, this.fourByFourMatrix2.multiply(this.scalarValueToMultiply)),
                () -> assertEquals(this.expectedProductThreeByThreeMatrixAndScalarValue, this.threeByThreeMatrix.multiply(this.scalarValueToMultiply)),
                () -> assertEquals(this.expectedProductTwoByTwoMatrixAndScalarValue, this.twoByTwoMatrix.multiply(this.scalarValueToMultiply)),
                () -> assertEquals(this.expectedProductOneElementMatrixAndScalarValue, this.oneElementMatrix.multiply(this.scalarValueToMultiply))
        );
    }
    private final Matrix expectedDifferenceFourByFourMatrix1And2 = new Matrix(new double[][] {
            {-1, 2, 2, -3},
            {4, 0, 8, 10},
            {1, -1, 1, 0},
            {0, -1, -8, 0}
    });
    private final Matrix fourByFourZeroMatrix = new Matrix(this.fourByFourZeroDoubleArray);
    private final Matrix threeByThreeZeroMatrix = new Matrix(this.threeByThreeZeroDoubleArray);
    private final Matrix twoByTwoZeroMatrix = new Matrix(this.twoByTwoZeroDoubleArray);
    private final Matrix oneElementZeroMatrix = new Matrix(this.oneElementZeroDoubleArray);
    @Test
    void subtract() {
        assertAll("Subtract (throws):",
                () -> assertThrows(IllegalArgumentException.class, () -> this.fourByFourMatrix.subtract(this.threeByThreeMatrix)),
                () -> assertThrows(IllegalArgumentException.class, () -> this.threeByThreeMatrix.subtract(this.twoByTwoMatrix)),
                () -> assertThrows(IllegalArgumentException.class, () -> this.twoByTwoMatrix.subtract(this.oneElementMatrix))
        );
        assertAll("Subtract (equals):",
                () -> assertEquals(this.expectedDifferenceFourByFourMatrix1And2, this.fourByFourMatrix.subtract(this.fourByFourMatrix2)),
                () -> assertEquals(this.fourByFourZeroMatrix, this.fourByFourMatrix.subtract(this.fourByFourMatrix)),
                () -> assertEquals(this.fourByFourZeroMatrix, this.fourByFourMatrix2.subtract(this.fourByFourMatrix2)),
                () -> assertEquals(this.threeByThreeZeroMatrix, this.threeByThreeMatrix.subtract(this.threeByThreeMatrix)),
                () -> assertEquals(this.twoByTwoZeroMatrix, this.twoByTwoMatrix.subtract(this.twoByTwoMatrix)),
                () -> assertEquals(this.oneElementZeroMatrix, this.oneElementMatrix.subtract(this.oneElementMatrix))
        );
    }
    private final double expectedFourByFourMatrixDeterminant = -1334.00;
    private final double expectedFourByFourMatrix2Determinant = -234.00;
    private final double expectedThreeByThreeMatrixDeterminant = -18.00;
    private final double expectedTwoByTwoMatrixDeterminant = -1.25;
    private final double expectedOneElementMatrixDeterminant = 1.00;
    private final double wrongFourByFourMatrixDeterminant = -9999.99;
    private final double wrongFourByFourMatrix2Determinant = -8888.88;
    private final double wrongThreeByThreeMatrixDeterminant = -99.99;
    private final double wrongTwoByTwoMatrixDeterminant = -9.99;
    private final double wrongOneElementMatrixDeterminant = -9.99;
    private final double deviation = 0.01;
    @Test
    void determinant() {
        assertAll("Determinant (true):",
                () -> assertTrue((this.expectedFourByFourMatrixDeterminant - this.fourByFourMatrix.determinant()) <= this.deviation),
                () -> assertTrue((this.expectedFourByFourMatrix2Determinant - this.fourByFourMatrix2.determinant()) <= this.deviation),
                () -> assertTrue((this.expectedThreeByThreeMatrixDeterminant - this.threeByThreeMatrix.determinant()) <= this.deviation),
                () -> assertTrue((this.expectedTwoByTwoMatrixDeterminant - this.twoByTwoMatrix.determinant()) <= this.deviation),
                () -> assertTrue((this.expectedOneElementMatrixDeterminant - this.oneElementMatrix.determinant()) <= this.deviation)
        );
        assertAll("Determinant (false):",
                () -> assertFalse((this.wrongFourByFourMatrixDeterminant - this.fourByFourMatrix.determinant()) > this.deviation),
                () -> assertFalse((this.wrongFourByFourMatrix2Determinant - this.fourByFourMatrix2.determinant()) > this.deviation),
                () -> assertFalse((this.wrongThreeByThreeMatrixDeterminant - this.threeByThreeMatrix.determinant()) > this.deviation),
                () -> assertFalse((this.wrongTwoByTwoMatrixDeterminant - this.twoByTwoMatrix.determinant()) > this.deviation),
                () -> assertFalse((this.wrongOneElementMatrixDeterminant - this.oneElementMatrix.determinant()) > this.deviation)
        );
    }
    private final Matrix expectedFourByFourMatrixInverted = new Matrix(new double[][] {
            {-0.28, 0.10, -0.00, 0.25},
            {0.12, -0.07, -0.02, 0.25},
            {0.27, 0.05, -0.07, -0.27},
            {-0.03, -0.02, 0.07, 0.02}
    });
    private final Matrix expectedFourByFourMatrix2Inverted = new Matrix(new double[][] {
            {0.40, 0.12, -0.16, -0.05},
            {-2.67, 1.00, 1.11, 0.11},
            {1.08, -0.46, -0.46, 0.08},
            {0.09, -0.04, 0.02, -0.02}
    });
    private final Matrix expectedThreeByThreeMatrixInverted = new Matrix(new double[][] {
            {0.11, 0.28, -0.17},
            {0.56, -0.61, 0.17},
            {-0.44, 0.39, 0.17}
    });
    private final Matrix expectedTwoByTwoMatrixInverted = new Matrix(new double[][] {
            {-1.77, 3.52},
            {1.16, -1.85}
    });
    @Test
    void invert() {
        assertAll("Invert (true):",
                () -> assertTrue(this.expectedFourByFourMatrixInverted.equals(this.fourByFourMatrix.invert())),
                () -> assertTrue(this.expectedFourByFourMatrix2Inverted.equals(this.fourByFourMatrix2.invert())),
                () -> assertTrue(this.expectedThreeByThreeMatrixInverted.equals(this.threeByThreeMatrix.invert())),
                () -> assertTrue(this.expectedTwoByTwoMatrixInverted.equals(this.twoByTwoMatrix.invert()))
        );
        assertAll("Invert (false):",
                () -> assertFalse(this.fourByFourZeroMatrix.equals(this.fourByFourMatrix.invert())),
                () -> assertFalse(this.fourByFourZeroMatrix.equals(this.fourByFourMatrix2.invert())),
                () -> assertFalse(this.threeByThreeZeroMatrix.equals(this.threeByThreeMatrix.invert())),
                () -> assertFalse(this.twoByTwoZeroMatrix.equals(this.twoByTwoMatrix.invert()))
        );
    }
    private final Matrix expectedFourByFourMatrixTransposed = new Matrix(new double[][] {
            {1, 8, 2, 2},
            {3, 1, 1, 3},
            {4, 9, 4, 1},
            {5, 10, 19, 2}
    });
    private final Matrix expectedFourByFourMatrix2Transposed = new Matrix(new double[][] {
            {2, 4, 1, 2},
            {1, 1, 2, 4},
            {2, 1, 3, 9},
            {8, 0, 19, 2}
    });
    private final Matrix expectedThreeByThreeMatrixTransposed = new Matrix(new double[][] {
            {3, 3, 1},
            {2, 1, 3},
            {1, 2, 4}
    });
    private final Matrix expectedTwoByTwoMatrixTransposed = new Matrix(new double[][] {
            {2.31, 1.45},
            {4.38, 2.21}
    });
    @Test
    void transpose() {
        assertAll("Transpose (equals):",
                () -> assertEquals(this.expectedFourByFourMatrixTransposed, this.fourByFourMatrix.transpose()),
                () -> assertEquals(this.expectedFourByFourMatrix2Transposed, this.fourByFourMatrix2.transpose()),
                () -> assertEquals(this.expectedThreeByThreeMatrixTransposed, this.threeByThreeMatrix.transpose()),
                () -> assertEquals(this.expectedTwoByTwoMatrixTransposed, this.twoByTwoMatrix.transpose())
        );
        assertAll("Transpose (not equals):",
                () -> assertNotEquals(this.fourByFourZeroMatrix, this.fourByFourMatrix.transpose()),
                () -> assertNotEquals(this.fourByFourZeroMatrix, this.fourByFourMatrix2.transpose()),
                () -> assertNotEquals(this.threeByThreeZeroMatrix, this.threeByThreeMatrix.transpose()),
                () -> assertNotEquals(this.twoByTwoZeroMatrix, this.twoByTwoMatrix.transpose())
        );
    }
}