package expression;

public class Pow extends AbstractUnaryOperation {
    protected Pow(Operand operand) {
        super(operand);
    }

    @Override
    protected int calc(int first) {
        return fastPow(10, first);
    }

    @Override
    public double evaluate(double x) {
        return Math.pow(10, x);
    }

    @Override
    public int getFirstPriority() {
        return 11;
    }

    @Override
    public boolean isSpecialOperation() {
        return false;
    }

    @Override
    public String getOperation() {
        return "pow10";
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
