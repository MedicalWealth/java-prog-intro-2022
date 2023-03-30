package expression.exceptions;

import expression.Operand;
import expression.Subtract;
import expression.TripleExpression;
import expression.Variable;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(Operand firstOperand, Operand secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int first, int second) {
        int result = first - second;
        if (first < 0 && second > 0 && result >= 0 || first >= 0 && second < 0 && result <= 0) {
            throw new OverflowException("subtract", first, second);
        }
        return first - second;
    }
}
