package expression;

public class Const extends AbstractValue {
    private final Number constant;

    public Const(int constant) {
        this.constant = constant;
    }

    public Const(double constant) {
        this.constant = constant;
    }

    @Override
    public double evaluate(double variable) {
        return constant.doubleValue();
    }

    @Override
    public int calc(int... variables) {
        return constant.intValue();
    }

    @Override
    public String toString() {
        return String.valueOf(constant);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof final Const con) {
            return constant.equals(con.constant);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return constant.hashCode();
    }
}
