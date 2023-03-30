package expression.exceptions;

import expression.Divide;
import expression.Operand;
import expression.TripleExpression;
import expression.Variable;

public class CheckedDivide extends Divide {
    public CheckedDivide(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int first, int second) {
        if (second == 0) {
            throw new DBZException();
        } else if (first == Integer.MIN_VALUE && second == -1) {
            throw new OverflowException("divide", first, second);
        }
        return first / second;
    }
}
