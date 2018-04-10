public class Program {
    public static void main (String argv[]) {
        final double scalarValueToMultiply = 12.34;
        Matrix matrixA = new Matrix(new double[][] {
                { 3, 2, 1 },
                { 3, 1, 2 },
                { 1, 3, 4 }
        });
        Matrix matrixB = new Matrix(new double[][] {
                { 3, 2, 3 },
                { 4, 1, 2 },
                { 1, 5, 4 }
        });
        Matrix expectedResultMatrix = new Matrix(new double[][] {
                { -36.91, -49.08, -12.50 },
                { -24.12, -12.95, -61.53 },
                { -37.46, -24.29, -49.19 }
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