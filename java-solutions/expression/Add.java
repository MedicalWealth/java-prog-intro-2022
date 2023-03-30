package expression;


public class Add extends AbstractBinaryOperation {
    public Add(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(final int first, final int second) {
        return first + second;
    }

    @Override
    public double evaluate(double x) {
        return firstOperand.evaluate(x) + secondOperand.evaluate(x);
    }

    @Override
    public int getFirstPriority() {
        return 3;
    }

    @Override
    public boolean isSpecialOperation() {
        return false;
    }

    @Override
    public String getOperation() {
        return "+";
    }
}
