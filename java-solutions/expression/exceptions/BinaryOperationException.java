package expression.exceptions;

public class BinaryOperationException extends ParseException {
    public BinaryOperationException(String found, int pos) {
        super("expected correct binary operation, but found: " + found + ", in pos = " + pos);
    }
}
