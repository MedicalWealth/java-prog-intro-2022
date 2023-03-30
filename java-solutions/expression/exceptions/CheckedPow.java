package expression.exceptions;

import expression.Operand;
import expression.Pow;

public class CheckedPow extends Pow {
    protected CheckedPow(Operand operand) {
        super(operand);
    }

    @Override
    public int calc(int evaluated) throws EvaluateException {
        if (evaluated >= 10) {
            throw new OverflowException("pow", evaluated);
        } else if (evaluated < 0) {
            throw new UndefinedException("pow", evaluated);
        }
        return fastPow(10, evaluated);
    }

    public static int fastPow(int a, int b) {
        int result = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                result *= a;
            }
            a *= a;
            b /= 2;
        }
        return result;
    }
}
