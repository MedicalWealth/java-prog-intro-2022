package expression;

public abstract class AbstractValue implements Operand {
    @Override
    public int evaluate(int x, int y, int z) {
        return calc(x, y, z);
    }

    protected abstract int calc(int... variables);

    @Override
    public int evaluate(int variable) {
        return calc(variable);
    }

    @Override
    public int getFirstPriority() {
        return 10;
    }

    @Override
    public boolean isSpecialOperation() {
        return false;
    }
}
