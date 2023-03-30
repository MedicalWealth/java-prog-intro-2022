package expression;

public class Log extends AbstractUnaryOperation {
    protected Log(Operand operand) {
        super(operand);
    }

    @Override
    protected int calc(int first) {
        return (int) Math.log10(first);
    }

    @Override
    public double evaluate(double x) {
        return Math.log10(x);
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
    public String getOperation() {
        return "log10";
    }
}
