package expression.exceptions;

import expression.Clear;
import expression.Operand;

public class CheckedClear extends Clear {
    public CheckedClear(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public int calc(int first, int second) throws EvaluateException {
        return first & ~(1 << second);
    }
}
