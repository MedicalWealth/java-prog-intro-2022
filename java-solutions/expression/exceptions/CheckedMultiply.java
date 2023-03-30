package expression.exceptions;

import expression.Multiply;
import expression.Operand;
import expression.TripleExpression;
import expression.Variable;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int first, int second) {
        int result = first * second;
        if (second != 0 && result / second != first || first != 0 && result / first != second) {
            throw new OverflowException("multiply", first, second);
        }
        return first * second;
    }
}
