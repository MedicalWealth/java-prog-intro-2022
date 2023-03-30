package expression.exceptions;

public class DBZException extends EvaluateException {
    public DBZException() {
        super("division by zero");
    }
}
