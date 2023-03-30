package expression.exceptions;

public class ParseException extends Exception {
    public int pos;

    public ParseException(String message, int pos) {
        super(message);
        this.pos = pos;
    }
}
