package expression.exceptions;

import expression.Log;
import expression.Operand;

public class CheckedLog extends Log {
    protected CheckedLog(Operand operand) {
        super(operand);
    }

    @Override
    protected int calc(int first) {
        if (first <= 0) {
            throw new UndefinedException("log", first);
        }
        int result = 0;
        while (first != 0) {
            result += 1;
            first /= 10;
        }
        return result - 1;
    }
}
