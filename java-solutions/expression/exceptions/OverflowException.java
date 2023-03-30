package expression.exceptions;

public class OverflowException extends EvaluateException {
    public OverflowException(String operation, int first, int second) {
        super("integer overflow in operation: " + operation + ", because arg1 = " + first + ", arg2 = " + second);
    }

    public OverflowException(String operation, int evaluated) {
        super("integer overflow in operation: " + operation + ", because arg = " + evaluated);
    }

    public OverflowException(String constant) {
        super("int overflow for constant = " + constant);
    }
}
