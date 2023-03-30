package expression;

public class Variable extends AbstractValue {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public Variable(char name) {
        this.name = String.valueOf(name);
    }

    @Override
    public double evaluate(double variable) {
        return variable;
    }

    @Override
    public int calc(int... variables) {
        if (variables.length == 1) {
            return variables[0];
        }
        switch (name) {
            case "x" -> {
                return variables[0];
            }
            case "y" -> {
                return variables[1];
            }
            case "z" -> {
                return variables[2];
            }
            default -> {
                throw new IllegalArgumentException("Not x, y, z");
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof final Variable variable) {
            return name.equals(variable.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
