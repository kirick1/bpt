public class Program {
    public static void main (String argv[]) {
        try {
            double[][] array = new double[][] {
                    { 20, 20, 43, 54 },
                    { 95, 36, 27, 38 },
                    { 19, 10, 11, 12 },
                    { 13, 14, 15, 16 }
            };
            Matrix matrix = new Matrix(array);
            System.out.print(matrix.toString());
            System.out.println("Determinant:");
            System.out.println(matrix.determinant());
            System.out.println("------------------");
            final double value = 12.34;
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
            matrixA = matrixA.inverse();
            matrixB = matrixB.transpose();
            matrixB = matrixB.multiply(value);
            Matrix result = matrixA.subtract(matrixB);
            System.out.print(result.toString());
            Matrix expected = new Matrix(new double[][] {
                    { -36.91, -49.08, -12.50 },
                    { -24.12, -12.95, -61.53 },
                    { -37.46, -24.29, -49.19 }
            });
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}