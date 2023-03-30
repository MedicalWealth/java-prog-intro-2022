package expression.exceptions;

public class NoBracketException extends ParseException {
    public NoBracketException(char bracket, String errorChar, int pos) {
        super("expected " + bracket + ", but found " + errorChar + " in pos = " + pos);
    }
}
