public class Program {
    public static void main (String argv[]) {
        final double scalarValueToMultiply = 19.73;
        Matrix matrixA = new Matrix(new double[][] {
                {9, 7, 5},
                {8, 3, 0},
                {14, 1, 10}
        });
        Matrix matrixB = new Matrix(new double[][] {
                {3, 15, 22},
                {6, 1, 2},
                {19, 5, 8}
        });
        Matrix expectedResultMatrix = new Matrix(new double[][] {
                {-59.26, -118.24, -374.84},
                {-295.78, -19.77, -98.74},
                {-433.99, -39.65, -157.78}
        });
        try {
            System.out.println("Initial matrixA:");
            System.out.println(matrixA.toString());
            System.out.println("Initial matrixB:");
            System.out.println(matrixB.toString());
            System.out.println("Expected result matrix:");
            System.out.println(expectedResultMatrix.toString());
            System.out.println("-----------------------------");
            matrixA = matrixA.invert();
            matrixB = matrixB.transpose();
            matrixB = matrixB.multiply(scalarValueToMultiply);
            Matrix result = matrixA.subtract(matrixB);
            System.out.println("Result matrix:");
            System.out.print(result.toString());
            System.out.println("-----------------------------");
            boolean comparisionResultAndExpected = result.equals(expectedResultMatrix);
            String isCorrect = ((comparisionResultAndExpected) ? "\u001B[32m" : "\u001B[31m") + comparisionResultAndExpected + "\u001B[0m";
            System.out.println("Is result matrix equals with expected matrix for variant task: " + isCorrect);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}