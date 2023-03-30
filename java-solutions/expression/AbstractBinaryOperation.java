package expression;

import expression.exceptions.CheckedDivide;

public abstract class AbstractBinaryOperation implements Operand {
    protected final Operand firstOperand;
    protected final Operand secondOperand;

    protected AbstractBinaryOperation(Operand firstOperand, Operand secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public int evaluate(int variable) {
        return calc(firstOperand.evaluate(variable), secondOperand.evaluate(variable));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calc(firstOperand.evaluate(x, y, z), secondOperand.evaluate(x, y, z));
    }

    protected abstract int calc(int first, int second);

    @Override
    public String toString() {
        return "(" + firstOperand.toString() + " " + this.getOperation() + " " + secondOperand.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractBinaryOperation) {
            final AbstractBinaryOperation operation = (AbstractBinaryOperation) obj;
            return operation.getClass() == this.getClass() &&
                firstOperand.equals(operation.firstOperand) && secondOperand.equals(operation.secondOperand);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (firstOperand.hashCode() * 17 + secondOperand.hashCode()) * 17 + this.getClass().hashCode();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        appendMiniString(sb, firstOperand, this.getFirstPriority() > firstOperand.getFirstPriority());
        sb.append(" ").append(this.getOperation()).append(" ");
        boolean isAppendable = this.getFirstPriority() > secondOperand.getFirstPriority() ||
                               this.getFirstPriority() == secondOperand.getFirstPriority() &&
                               (this.isSpecialOperation() || secondOperand.getClass() == Divide.class ||
                                                             secondOperand.getClass() == CheckedDivide.class);
        appendMiniString(sb, secondOperand, isAppendable);
        return sb.toString();
    }

    private void appendMiniString(StringBuilder sb, Operand operand, boolean isPriority) {
        if (isPriority) {
            sb.append("(").append(operand.toMiniString()).append(")");
        } else {
            sb.append(operand.toMiniString());
        }
    }
}
