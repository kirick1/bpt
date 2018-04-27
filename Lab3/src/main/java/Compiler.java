import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Stack;

public class Compiler {
    private HashMap<String, Object> vars = new HashMap<>();
    private Stack<Character> operatorStack = new Stack<>();
    private Stack<Node> operandStack = new Stack<>();

    private Object compile(String inputString) {
        try {
            if(inputString.contains("=")) {
                String[] stringParts = inputString.split("=");
                if(!stringParts[0].trim().matches("[a-zA-Z_][a-zA-Z_0-9]*") || stringParts.length != 2) throw new InvalidParameterException("Invalid Input");
                Object data = compile(stringParts[1].trim());
                vars.put(stringParts[0].trim(), data);
                return data;
            }
            parse(inputString);
            while(!this.operatorStack.empty()) this.moveOperationToOperands(this.operatorStack.pop());
            if(this.operandStack.size() == -1) throw new InvalidParameterException("Invalid operands number");
            Node root = this.operandStack.pop();
            return root.value();
        } catch(Exception e) {
            this.operandStack = new Stack<>();
            this.operatorStack = new Stack<>();
            return e.getMessage();
        }
    }
    private void parse(String inputString) {
        inputString = inputString.trim();
        int inputStringLength = inputString.length();
        for(int i = 0; i < inputStringLength; i++) {
            char c = inputString.charAt(i);
            switch(c) {
                case '-':
                    this.processOperator(c);
                    break;
                case '*':
                    this.processOperator(c);
                    break;
                case '^':
                    if(inputString.charAt(i + 1) == '-' && inputString.charAt(i + 2) == '1') {
                        i += 3;
                        this.processOperator('i');
                        break;
                    } else if(inputString.charAt(i + 1) == 'T') {
                        i += 2;
                        this.processOperator('t');
                        break;
                    } else throw new InvalidParameterException("Unexpected operation with matrix: " + inputString.charAt(i + 1));
                case '(':
                    this.operatorStack.push(c);
                    break;
                case ')':
                    this.processRightParenthesis();
                    break;
                case '[':
                    if(!inputString.contains("]]")) throw new InvalidParameterException("Invalid matrix");
                    int closeSymbolIndex = inputString.indexOf("]]", i) + 2;
                    String subString = inputString.substring(i, closeSymbolIndex);
                    if(subString.matches("\\[\\[\\d+\\.?\\d{0,2}(,\\s?\\d+\\.?\\d{0,2})*](,\\s?\\[\\d+\\.?\\d{0,2}(,\\s?\\d+\\.?\\d{0,2})*])*]")) {
                        operandStack.push(new DataNode(Matrix.convert(inputString.substring(i, closeSymbolIndex))));
                        i = closeSymbolIndex - 1;
                        break;
                    } else throw new InvalidParameterException("Invalid matrix");
                default:
                    if(Character.isLetter(c) || c == '_') {
                        int startPosition = i;
                        while(Character.isLetterOrDigit(c) || c == '_') {
                            i++;
                            c = (i != inputStringLength) ? inputString.charAt(i) : ' ';
                        }
                        String var = inputString.substring(startPosition, i);
                        if(!vars.containsKey(var)) throw new InvalidParameterException("Does not exist variable: " + var);
                        this.operandStack.push(new DataNode(vars.get(var)));
                        i--;
                    } else if(Character.isDigit(c)) {
                        int startPosition = i;
                        boolean isDot = false;
                        while(Character.isDigit(c) || c == '.') {
                            if(c == '.') {
                                if(!isDot) isDot = true;
                                else throw new InvalidParameterException("Unexpected character in position: " + i);
                            }
                            i++;
                            c = (i != inputStringLength) ? inputString.charAt(i) : ' ';
                        }
                        String doubleString = inputString.substring(startPosition, i);
                        this.operandStack.push(new DataNode(Double.parseDouble(doubleString)));
                    }
                    break;
            }
        }
    }
    private void processOperator(Character c) {
        int characterPriority = this.priority(c);
        while(!this.operatorStack.empty() && (characterPriority <= this.priority(this.operatorStack.peek()))) this.moveOperationToOperands(this.operatorStack.pop());
        this.operatorStack.push(c);
    }
    private void processRightParenthesis() {
        while(!this.operatorStack.empty() && (this.operatorStack.peek() != '(')) this.moveOperationToOperands(this.operatorStack.pop());
        this.operatorStack.pop();
    }
    private void moveOperationToOperands(Character c) {
        Node left;
        Node right;
        OperationNode node;
        if(c == 'i' || c == 't') {
            left = this.operandStack.pop();
            node = new OperationNode(c, left, null);
        } else {
            right = this.operandStack.pop();
            left = this.operandStack.pop();
            node = new OperationNode(c, left, right);
        }
        this.operandStack.push(node);
    }
    private int priority(Character c) {
        switch(c) {
            case '-': return 1;
            case 'i': return 2;
            case '*': return 3;
            case 't': return 4;
            default: return 0;
        }
    }
    public static void main(String[] args) {
        try {
            Compiler compiler = new Compiler();
            System.out.println("Initialization:\n" + compiler.compile("A = [[1, 1, 1], [1, 1, 1], [1, 1, 1]]"));
            System.out.println("Multiplication:\n" + compiler.compile("2 * ([[1, 1, 1], [1, 1, 1], [1, 1, 1]])"));
            System.out.println("Subtraction:\n" + compiler.compile("[[1, 1, 1], [1, 1, 1], [1, 1, 1]] - [[2, 2, 2], [2, 2, 2], [2, 2, 2]]"));
            System.out.println("Inverting:\n" + compiler.compile("([[3, 2, 1], [3, 1, 2], [1, 3, 4]])^-1"));
            System.out.println("Transposition:\n" + compiler.compile("([[3, 2, 1], [3, 1, 2], [1, 3, 4]])^T"));
            System.out.println("Solution:\n" + compiler.compile("([[1, 3, 4, 5], [8, 1, 9, 10], [2, 1, 4, 19], [2, 3, 1, 2]])^-1 - 33.33 * [[2, 1, 2, 8], [4, 1, 1, 0], [1, 2, 3, 19], [2, 4, 9, 2]]^T"));
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
