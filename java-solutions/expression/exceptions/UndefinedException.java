package expression.exceptions;

public class UndefinedException extends EvaluateException {

    public UndefinedException(final String operation, final int argument) {
        super("undefined behavior, because operation is " + operation + ", but argument = " + argument);
    }
}
