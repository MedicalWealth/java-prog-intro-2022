package expression;

public class Count extends AbstractUnaryOperation {
    public Count(Operand operand) {
        super(operand);
    }
    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException("Double can't count bits in double");
    }

    @Override
    public int getFirstPriority() {
        return 11;
    }

    @Override
    public boolean isSpecialOperation() {
        return false;
    }

    @Override
    public int calc(final int evaluated) {
        return Integer.bitCount(evaluated);
    }

    @Override
    public String getOperation() {
        return "count";
    }
}
