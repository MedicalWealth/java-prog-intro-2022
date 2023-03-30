package expression.exceptions;

import expression.Operand;
import expression.Set;

public class CheckedSet extends Set {
    public CheckedSet(Operand first, Operand second) {
        super(first, second);
    }

    @Override
    public int calc(int first, int second) throws EvaluateException {
        return first | (1 << second);
    }
}
