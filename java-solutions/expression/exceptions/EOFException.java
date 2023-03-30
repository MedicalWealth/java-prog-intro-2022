package expression.exceptions;

public class EOFException extends ParseException {
    public EOFException(String found, int pos) {
        super("expected end of expression, but found: " + found, pos);
    }
}
