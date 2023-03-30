package expression;

public interface Operand extends Expression, TripleExpression, DoubleExpression {
    int getFirstPriority();

    boolean isSpecialOperation();

    default String getOperation() {
        throw new UnsupportedOperationException("Class without operation");
    }
}
