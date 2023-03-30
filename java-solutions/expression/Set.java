package expression;

public class Set extends AbstractBinaryOperation {
    public Set (final Operand first, final Operand second) {
        super(first, second);
    }


    @Override
    public double evaluate(final double x) {
        throw new UnsupportedOperationException("Double can't set bit");
    }

    @Override
    public int getFirstPriority() {
        return 2;
    }

    @Override
    public boolean isSpecialOperation() {
        return true;
    }

    @Override
    public int calc(final int first, final int second) {
        return first | (1 << second);
    }

    @Override
    public String getOperation() {
        return "set";
    }
}
