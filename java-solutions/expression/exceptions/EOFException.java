package expression.exceptions;

public class EOFException extends ParseException {
    public EOFException(String found) {
        super("expected end of expression, but found: " + found);
    }
}
