package expression;


public class Negate extends AbstractUnaryOperation {
    public Negate(Operand operand) {
        super(operand);
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public double evaluate(double x) {
        return -1 * operand.evaluate(x);
    }

    @Override
    public int getFirstPriority() {
        return 9;
    }

    @Override
    public boolean isSpecialOperation() {
        return true;
    }

    @Override
    public int calc(final int evaluated) {
        return -1 * evaluated;
    }
}
