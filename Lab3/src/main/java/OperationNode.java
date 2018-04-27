import java.security.InvalidParameterException;

public class OperationNode extends Node {
    private char operation;
    private Node left;
    private Node right;
    OperationNode(char character, Node leftNode, Node rightNode) {
        this.operation = character;
        this.left = leftNode;
        this.right = rightNode;
    }
    public Object value() {
        Object leftValue = this.left.value();
        Object rightValue = (this.right != null) ? this.right.value() : null;
        Matrix matrixLeft = (leftValue instanceof Matrix) ? (Matrix) leftValue : null;
        Matrix matrixRight = (rightValue instanceof Matrix) ? (Matrix) rightValue : null;
        Double doubleLeft = (leftValue instanceof Double) ? (Double) leftValue : null;
        Double doubleRight = (rightValue instanceof Double) ? (Double) rightValue : null;
        switch(this.operation) {
            case '-':
                if(matrixLeft != null && matrixRight != null) return matrixLeft.subtract(matrixRight);
                else if(doubleLeft != null && doubleRight != null) return doubleLeft - doubleRight;
                else throw new InvalidParameterException("Cannot subtract");
            case 'i':
                if(matrixLeft != null) return matrixLeft.invert();
                else throw new InvalidParameterException("Cannot invert");
            case '*':
                if(matrixLeft != null && doubleRight != null) return matrixLeft.multiply(doubleRight);
                else if(doubleLeft != null && matrixRight != null) return matrixRight.multiply(doubleLeft);
                else if(matrixLeft != null && matrixRight != null) return matrixLeft.multiply(matrixRight);
                else if(doubleLeft != null && doubleRight != null) return doubleLeft * doubleRight;
                else throw new InvalidParameterException("Cannot multiply");
            case 't':
                if(matrixLeft != null) return matrixLeft.transpose();
                else throw new InvalidParameterException("Cannot transpose");
            default: return null;
        }
    }
}
