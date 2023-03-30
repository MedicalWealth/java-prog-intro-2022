package expression.exceptions;

public class PrimExpectedException extends ParseException {
    public PrimExpectedException(String found, int pos) {
        super("expected correct unary operation, opening bracket or number, but found: " + found + " in pos = " + pos, pos);
    }
}
