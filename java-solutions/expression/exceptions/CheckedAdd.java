package expression.exceptions;

import expression.Add;
import expression.Operand;
public class CheckedAdd extends Add {
    public CheckedAdd(final Operand first, final Operand second) {
        super(first, second);
    }

    @Override
    public int calc(final int first, final int second) throws EvaluateException {
        final int result = first + second;
        if (first < 0 && second < 0 && result >= 0 || first > 0 && second > 0 && result <= 0) {
            throw new OverflowException("add", first, second);
        }
        return result;
    }
}
