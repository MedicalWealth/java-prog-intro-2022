package expression;

public abstract class AbstractUnaryOperation implements Operand {
    protected final Operand operand;

    protected AbstractUnaryOperation(Operand operand) {
        this.operand = operand;
    }

    @Override
    public int evaluate(int x) {
        return calc(operand.evaluate(x));
    }

    protected abstract int calc(int first);

    @Override
    public int evaluate(int x, int y, int z) {
        return calc(operand.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return getOperation() + "(" + operand.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (operand instanceof AbstractBinaryOperation) {
            return getOperation() + "(" + operand.toMiniString() + ")";
        }
        return getOperation()+ " " + operand.toMiniString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractUnaryOperation) {
            final AbstractUnaryOperation operation = (AbstractUnaryOperation) obj;
            return operation.getClass() == this.getClass() && operand.equals(operation.operand);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return operand.hashCode() * 17 + this.getClass().hashCode();
    }
}
