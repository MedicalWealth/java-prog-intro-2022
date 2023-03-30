package expression;

public class Multiply extends AbstractBinaryOperation {

    public Multiply(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(final int first, final int second){
        return first * second;
    }

    @Override
    public double evaluate(double variable) {
        return firstOperand.evaluate(variable) * secondOperand.evaluate(variable);
    }

    @Override
    public int getFirstPriority() {
        return 4;
    }

    @Override
    public boolean isSpecialOperation() {
        return false;
    }

    @Override
    public String getOperation() {
        return "*";
    }
}
