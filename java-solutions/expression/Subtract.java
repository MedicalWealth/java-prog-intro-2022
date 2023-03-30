package expression;


public class Subtract extends AbstractBinaryOperation {
    public Subtract(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(final int first, final int second) {
        return first - second;
    }

    @Override
    public double evaluate(double variable) {
        return firstOperand.evaluate(variable) - secondOperand.evaluate(variable);
    }

    @Override
    public int getFirstPriority() {
        return 3;
    }

    @Override
    public boolean isSpecialOperation() {
        return true;
    }

    @Override
    public String getOperation() {
        return "-";
    }
}
