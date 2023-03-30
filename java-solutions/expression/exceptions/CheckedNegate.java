package expression.exceptions;

import expression.Negate;
import expression.Operand;

public class CheckedNegate extends Negate {
    public CheckedNegate(Operand operand) {
        super(operand);
    }

    @Override
    public int calc(int evaluated) {
        if (evaluated == Integer.MIN_VALUE) {
            throw new OverflowException("negate", evaluated);
        }
        return -1 * evaluated;
    }
}
